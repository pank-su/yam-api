package su.pank.yamapi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import su.pank.yamapi.account.AccountApi
import su.pank.yamapi.album.AlbumsApi
import su.pank.yamapi.builder.updateToken
import su.pank.yamapi.exceptions.NotAuthenticatedException
import su.pank.yamapi.landing.LandingApi
import su.pank.yamapi.model.*
import su.pank.yamapi.model.search.*
import su.pank.yamapi.playlist.PlaylistsApi
import su.pank.yamapi.playlist.model.TagResult
import su.pank.yamapi.track.Track
import su.pank.yamapi.track.TracksApi
import su.pank.yamapi.utils.setBody

/**
 * Базовый класс для выполнения запросов к API Яндекс.Музыки.
 */
abstract class YaRequester {
    internal abstract val httpClient: HttpClient

    // ёбидоёби

    /**
     * Получение тела из поля result возвращаемых данных
     */
    internal suspend inline fun <reified T> HttpResponse.yabody(): T = this.body<BasicResponse<T>>().result

    // Настроенные get на установку тела как параметров

    internal suspend inline fun <reified T> get(
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T =
        httpClient
            .get {
                url {
                    path(*path)
                }
                block()
            }.yabody()

    internal suspend inline fun <reified T, reified R> get(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T =
        httpClient
            .get {
                url {
                    path(*path)

                    parameters.setBody(body)
                }
                block()
            }.yabody()

    internal suspend inline fun <reified T, reified R> postForm(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T =
        httpClient
            .submitForm(
                formParameters = parameters { setBody(body) },
            ) {
                url {
                    path(*path)
                }

                method = HttpMethod.Post
                block()
            }.yabody()

    internal suspend inline fun <reified T, reified R> form(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T =
        httpClient
            .submitForm(
                formParameters = parameters { setBody(body) },
            ) {
                url {
                    path(*path)
                }

                method = HttpMethod.Get
                block()
            }.yabody()
}

/**
 * Основной клиент для взаимодействия с API Яндекс.Музыки.
 *
 * @param httpClient HTTP клиент для выполнения запросов.
 * @param language Язык для запросов.
 */
class YamApiClient(
    override var httpClient: HttpClient,
    val language: Language,
    token: String?
) : YaRequester() {



    /**
     * Поток токена для обновления зависимых от токена данных
     */
    private val _token = MutableStateFlow<String?>(token)

    /**
     * Токен авторизации для горячего обновления его в клиенте
     */
    var token: String?
        get() = _token.value
        set(value){
            httpClient = httpClient.config { updateToken(value) }
            _token.value = value
        }

    init {
        this.token = token
    }

    /**
     * Scope для хранения состояний зависящих от токена
     */
    private val tokenScope = CoroutineScope(Dispatchers.Default)

    /**
     * Идентификатор пользователя
     *
     * Используется для взаимодействия с плейлистом, лайками и т.п.
     */
    val userId = _token.map {
        if (it != null) {
            account.status().account.uid.toString()
        }
        else null
    }.stateIn(tokenScope, SharingStarted.Eagerly, null)

    /**
     * API для работы с аккаунтом пользователя.
     */
    val account: AccountApi = AccountApi(this)

    /**
     * API для работы с лендингом.
     */
    val landing: LandingApi = LandingApi(this)

    /**
     * API для работы с плейлистами.
     */
    val playlists: PlaylistsApi = PlaylistsApi(this)

    /**
     * API для работы с альбомами.
     */
    val albums: AlbumsApi = AlbumsApi(this)

    /**
     * API для работы с треками.
     */
    val tracks: TracksApi = TracksApi(this)

    /**
     * Получает список жанров.
     *
     * @return Список жанров.
     */
    suspend fun genres(): List<Genre> = get("genres")

    /**
     * Получает плейлисты по тегу.
     *
     * @param tagId Идентификатор тега.
     * @return Результат с плейлистами.
     */
    suspend fun tags(tagId: String) = get<TagResult>("tags", tagId, "playlist-ids")

    /**
     * Выполняет поиск с использованием билдера запроса.
     *
     * @param query Строка запроса.
     * @param builder Билдер для настройки запроса.
     * @return Результаты поиска.
     */
    suspend fun search(
        query: String,
        builder: SearchRequestBuilder.() -> Unit,
    ) = search(SearchRequestBuilder().apply { builder() }.build(query))

    private suspend fun search(searchRequest: SearchRequest): Search =
        get(
            searchRequest,
            "search",
        )

    /**
     * Выполняет поиск.
     *
     * @param query Строка запроса.
     * @param isCorrect Корректировать ли запрос.
     * @param type Тип поиска.
     * @param page Номер страницы.
     * @param playlistInBest Включать ли плейлисты в лучшие результаты.
     * @return Результаты поиска.
     */
    suspend fun search(
        query: String,
        isCorrect: Boolean = false,
        type: QueryType = QueryType.All, // TODO: сделать выбор что искать с помощью типов, что может помочь сохранить типизацию
        page: Int = 0,
        playlistInBest: Boolean = false,
    ) = search(SearchRequest(query, isCorrect, type, page, playlistInBest))

    /**
     * Получает предложения для поиска.
     *
     * @param part Часть запроса.
     * @return Предложения.
     */
    suspend fun searchSuggest(part: String) =
        get<Suggestions>("search", "suggest") {
            parameter("part", part)
        }

    /**
     * Получает полную информацию о пользователе.
     *
     * @return Информация о пользователе.
     */
    suspend fun userInfo() = httpClient.get("https://login.yandex.ru/" + "info").body<UserInfo>()

    /**
     * Получить userId или использовать заданный
     */
    internal fun resolveUserId(userId: String? = null): String =
        userId ?: this.userId.value ?: throw NotAuthenticatedException()

    /**
     * Выполняет действие лайка или удаления лайка для [Likable].
     */
    internal suspend inline fun <reified T : Likable> likeAction(
        vararg ids: String,
        userId: String? = null,
        action: LikeAction = LikeAction.`add-multiple`,
    ): Boolean {
        val userId = resolveUserId(userId)
        val name = T::class.simpleName?.lowercase()
        val body = hashMapOf("$name-ids" to ids.toList())

        val result =
            postForm<String, HashMap<String, List<String>>>(body, "users", userId, "likes", "${name}s", action.name)
        if (T::class == Track::class) {
            return "revision" in result
        }
        return result == "ok"
    }

    internal suspend inline fun <reified T : Likable> like(
        vararg ids: String,
        userId: String? = null,
    ): Boolean = likeAction<T>(*ids, userId = userId)

    internal suspend inline fun <reified T : Likable> unlike(
        vararg ids: String,
        userId: String? = null,
    ): Boolean = likeAction<T>(*ids, userId = userId, action = LikeAction.remove)
}
