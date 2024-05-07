
import dsl.YandexMusicTagMaker
import exceptions.NotAuthenticatedException
import exceptions.SessionExpiredException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.*
import model.account.PromoCodeStatus
import model.account.Status
import model.account.UserSettings
import model.ad.Ad
import model.album.Album
import model.downloadInfo.DownloadInfo
import model.feed.Feed
import model.genre.Genre
import model.landing.*
import model.playlist.Playlist
import model.playlist.TagResult
import model.rotor.Dashboard
import model.rotor.Session
import model.rotor.StationResult
import model.search.QueryType
import model.search.Search
import model.search.Suggestions
import model.supplement.Supplement
import model.track.SimilarTracks
import model.track.Track
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import utils.removeCarets


expect fun getHttpClientEngine(): HttpClientEngine

/**
 * Клиент для работы с Яндекс Музыкой
 *
 * @property baseUrl ссылка на api Яндекс Музыки
 * @property token токен пользователя
 * @property language выбранный язык TODO: переделать на тип Language
 * @property me текущий статус пользователя, получается при корректном токене
 * @property httpClientEngine клиент
 * @property loggingSettings настройки логирования
 * @property requestSettings дополнительные настройки запроса
 *
 *
 */
@YandexMusicTagMaker
class Client {
    var baseUrl: String = "https://api.music.yandex.net"
    var token: String = ""
    var language = "ru"
    var me: Status? = null


    var httpClientEngine: HttpClientEngine = getHttpClientEngine()

    var loggingSettings: LoggingConfig.() -> Unit = {
        logger = Logger.DEFAULT
        this.level = LogLevel.BODY
    }

    var requestSettings: HttpRequestBuilder.() -> Unit = {

    }

    internal var httpClient = HttpClient(httpClientEngine) {
        install(Logging) {
            loggingSettings()
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    /**
     * Запрос примитива, то есть в ответе будет список или другой тип.
     *
     * @param components компоненты ссылки
     * @param method HTTP метод
     * @param body тело запроса
     *
     * @see BasicResponsePrimitive
     */
    private suspend inline fun <reified T> requestPrimitive(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): ResultPrimitive<T> {
        return httpClient.request(baseUrl) {
            basicSettings(method, components.toList())
            if (method == HttpMethod.Post) {

            } else if (method == HttpMethod.Get) {
                url {
                    body.forEach {
                        parameters.append(it.key, it.value)
                    }
                }
            }
        }.body<BasicResponsePrimitive<T>>().result.apply { client = this@Client }
    }

    /**
     * Запрос примитива с помощью формы (например отправка новых настроек пользователя)
     *
     * @param components компоненты ссылки
     * @param method HTTP метод
     * @param body тело запроса
     *
     * @see BasicResponsePrimitive
     */
    private suspend inline fun <reified T> requestPrimitiveForm(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): ResultPrimitive<T> {
        return httpClient.submitForm(baseUrl, formParameters = parameters {
            body.forEach {
                append(it.key, it.value)
            }
        }) {
            basicSettings(method, components.toList())
        }.body<BasicResponsePrimitive<T>>().result.apply { client = this@Client }
    }

    /**
     * Базовые настройки запроса.
     *
     * @param components компоненты ссылки
     * @param method HTTP метод
     */
    private fun HttpRequestBuilder.basicSettings(
        method: HttpMethod,
        components: List<String>
    ) {
        requestSettings()
        this.method = method
        url {
            appendPathSegments(components.toList())
        }
        headers {
            append("X-Yandex-Music-Client", "YandexMusicAndroid/24022571")
            append("USER_AGENT", "Yandex-Music-API")
            append("Accept-Language", language)

            if (token != "") {
                append(HttpHeaders.Authorization, "OAuth $token")
            }
        }
    }


    /**
     * Запрос, который возвращает объект описанных моделей
     *
     * @param components компоненты ссылки
     * @param method HTTP метод
     * @param body тело запроса
     *
     * @see Result
     */
    private suspend inline fun <reified T : Result> request(
        components: List<String>,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.request(baseUrl) {
            basicSettings(method, components)
            if (method == HttpMethod.Post) {
                contentType(ContentType.Application.Json)
                val body = buildString {
                    append("{")

                    body.forEach {
                        append("\"${it.key}\": ${it.value}")
                        append(",")
                    }
                    this.setLength(length - 1)
                    append("}")
                }
                setBody(body)
            } else if (method == HttpMethod.Get) {
                url {
                    body.forEach {
                        parameters.append(it.key, it.value)
                    }
                }
            }
        }.body<BasicResponse<T>>().result.apply { client = this@Client }
    }

    /**
     * Запрос формы, который возвращает объект описанных моделей
     *
     * @param components компоненты ссылки
     * @param body тело запроса
     *
     * @see Result
     */
    internal suspend inline fun <reified T : Result> requestForm(
        vararg components: String,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.submitForm(baseUrl, formParameters =
        parameters {
            body.forEach {
                append(it.key, it.value)
            }
        }) {
            requestSettings()
            url {
                appendPathSegments(components.toList())
            }
            headers {
                append("X-Yandex-Music-Client", "YandexMusicAndroid/24022571")
                append("USER_AGENT", "Yandex-Music-API")
                if (token != "") {
                    append(HttpHeaders.Authorization, "OAuth $token")
                }
            }
        }.body<BasicResponse<T>>().result.apply { client = this@Client }
    }

    /**
     * Упрощение запроса request
     *
     * @see request
     */
    private suspend inline fun <reified T : Result> request(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get
    ): T {
        return request(components.toList(), method)
    }

    /**
     * Упрощение запроса request
     *
     * @see request
     */
    internal suspend inline fun <reified T : Result> requestPost(
        vararg components: String,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return request(components.toList(), method = HttpMethod.Post, body)
    }


    /**
     * Инициализация клиента для определения настроек
     *
     */
    suspend fun init(init: Client.() -> Unit) {
        this.init()
        try {
            me = status()
        } catch (e: SessionExpiredException) {
            println("Токен некоректный или истёк") // TODO: replace to normal logger
        }
    }

    /**
     * Запрос статуса пользователя (/account/status)
     *
     * @see Status
     */
    suspend fun status() = request<Status>("account", "status")

    /**
     * Запрос настройка пользователя (/account/settings)
     *
     * @see UserSettings
     */
    suspend fun userSettings() = request<UserSettings>("account", "settings")

    /**
     * Запрос рекламы пользователя (/settings)
     *
     * @see Ad
     */
    suspend fun ads() = request<Ad>("settings")


    suspend fun permissionAlerts() = request<PermissionAlerts>("permission-alerts")

    /**
     * Запрос экспериментов для данного аккаунта
     *
     * @return словарь всех экспериментов
     */
    suspend fun accountExperiments() = requestPrimitive<HashMap<String, String>>("account", "experiments")

    /**
     * Активация промокода
     *
     * @param code промокод
     *
     * @see PromoCodeStatus
     */
    suspend fun consumePromoCode(code: String) = requestForm<PromoCodeStatus>(
        "account",
        "consume-promo-code",
        body = hashMapOf("code" to code, "language" to language)
    )

    /**
     * Получение сгенерированных плейлистов
     *
     * @see Feed
     */
    @Deprecated("Лучше использовать landing функции", level = DeprecationLevel.WARNING)
    suspend fun feed() = request<Feed>("feed")

    suspend fun feedWizardIsPassed() =
        requestPrimitive<HashMap<String, Boolean>>("feed", "wizard", "is-passed").value!!["isWizardPassed"] as Boolean

    /**
     * Получение landing'а по различным блокам. Для этого также можно использовать отдельные запросы.
     *
     * @param blocks получаемые блоки, в зависимости от выбранных блоков, будет разный контент
     *
     * @see Landing
     */
    suspend fun landing(vararg blocks: BlockType) =
        request<Landing>(
            listOf("landing3"),
            body = hashMapOf(
                "blocks" to blocks.joinToString(",") {
                    Json.encodeToString(it).removeCarets()

                },
                "eitherUserId" to (me?.account?.uid ?: throw NotAuthenticatedException()).toString()
            )
        )

    suspend fun chart(chartOption: ChartOption? = null) =
        request<ChartInfo>(
            "landing3", "chart", if (chartOption != null) {
                Json.encodeToString(chartOption).removeCarets()
            } else ""
        )

    suspend fun newReleases() = request<LandingList>("landing3", "new-releases")

    suspend fun newPlaylists() = request<LandingList>("landing3", "new-playlists")

    suspend fun podcasts() = request<LandingList>("landing3", "podcasts")

    suspend fun genres() = requestPrimitive<List<Genre>>("genres")

    suspend fun tags(tagId: String) = request<TagResult>("tags", tagId, "playlist-ids")

    suspend fun tracksDownloadInfo(trackId: Int) =
        requestPrimitive<List<DownloadInfo>>("tracks", trackId.toString(), "download-info")

    suspend fun tracksDownloadInfoNew(
        trackId: Int,
        canUseStreaming: Boolean = false
    ): ResultPrimitive<List<DownloadInfo>> {
        val timestamp = Clock.System.now().epochSeconds
        val hmacSign = HmacSHA256("p93jhgh689SBReK6ghtw62".encodeToByteArray()) // HmacSHA256()
        val sign = hmacSign.doFinal("${trackId}${timestamp}".encodeToByteArray()).encodeBase64()


        return requestPrimitive<List<DownloadInfo>>(
            "tracks",
            trackId.toString(),
            "download-info",
            body = hashMapOf(
                "can_use_streaming" to canUseStreaming.toString().lowercase(),
                "ts" to timestamp.toString(),
                "sign" to sign
            ),
        )

    }



    suspend fun trackSupplement(trackId: Int) = request<Supplement>("tracks", trackId.toString(), "supplement")

    suspend fun tracksLyrics(trackId: Int, format: String = "TEXT"): Result =
        TODO("Необходима реализация get_sign_request")

    suspend fun tracksSimilar(trackId: Int) = request<SimilarTracks>("tracks", trackId.toString(), "similar")

    suspend fun playAudio(
        trackId: Int,
        from: String,
        albumId: Int,
        playlistId: String? = null,
        fromCache: Boolean = false,
        playId: String? = null,
        uid: Int? = null,
        timestamp: Instant? = null,
        trackLengthSeconds: Int = 0,
        totalPlayedSeconds: Int = 0,
        endPositionSeconds: Int = 0,
        clientNow: Instant? = null
    ) {
        TODO("Реализация позже")
    }

    suspend fun albumsWithTracks(albumId: Int) = request<Album>("albums", albumId.toString(), "with-tracks")

    suspend fun search(
        query: String,
        isCorrect: Boolean = false,
        type: QueryType = QueryType.All,
        page: Int = 0,
        playlistInBest: Boolean = false
    ) = request<Search>(
        listOf("search"),
        body = hashMapOf(
            "text" to query,
            "nocorrect" to isCorrect.toString().lowercase(),
            "type" to type.name.lowercase(),
            "page" to page.toString(),
            "playlist-in-best" to playlistInBest.toString().lowercase()
        )
    ).apply {
        this.type = type
        this.page = page
    }

    suspend fun searchSuggest(part: String) =
        request<Suggestions>(listOf("search", "suggest"), body = hashMapOf("part" to part))

    private suspend fun usersSettings(): Result = TODO("с версии 0.1.5")

    suspend fun userPlaylists(vararg kinds: Int, userId: Int? = null) = requestPrimitiveForm<List<Playlist>>(
        "users",
        (userId ?: me?.account?.uid ?: throw NotAuthenticatedException()).toString(),
        "playlists",
        method = HttpMethod.Post,
        body = hashMapOf("kinds" to kinds.joinToString(","))
    )

    suspend fun userPlaylist(kind: Int, userId: Int? = null) = request<Playlist>(
        "users",
        (userId ?: me?.account?.uid ?: throw NotAuthenticatedException()).toString(),
        "playlists",
        kind.toString()
    )


    private suspend fun usersPlaylistsRecommendations(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsCreate(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsDelete(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsName(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsVisibility(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsChange(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsInsertTrack(): Result = TODO("с версии 0.1.5")

    private suspend fun usersPlaylistsDeleteTrack(): Result = TODO("с версии 0.1.5")

    suspend fun rotorAccountStatus() = request<Status>("rotor", "account", "status")

    suspend fun rotorStationsDashboard() = request<Dashboard>("rotor", "stations", "dashboard")

    suspend fun rotorStations(language: Language = Language.ru) = requestPrimitive<List<StationResult>>(
        "rotor", "stations", "list",
        body = hashMapOf("language" to Json.encodeToString(language).removeCarets())
    )

    // TODO: работа с сессиями
    suspend fun rotorSessionNew(seeds: List<String>) =
        requestPost<Session>(
            "rotor",
            "session",
            "new",
            body = hashMapOf("seeds" to "[" + seeds.joinToString(",") { "\"$it\"" } + "]",
                "includeTracksInResponse" to "true")
        )


    suspend fun tracks(vararg trackIds: Int, withPositions: Boolean = true) = requestPrimitiveForm<List<Track>>(
        "tracks", method = HttpMethod.Post,
        body = hashMapOf("with-positions" to withPositions.toString(), "track-ids" to trackIds.joinToString(","))
    )

    // полное получение информации о пользователе
    suspend fun userInfo() = httpClient.get("https://login.yandex.ru/" + "info") {
        headers { append(HttpHeaders.Authorization, "OAuth $token") }
    }.body<UserInfo>()

    suspend fun playlistList(vararg playlistIds: String) = requestPrimitiveForm<List<Playlist>>(
        "playlists",
        "list",
        method = HttpMethod.Post,
        body = hashMapOf("playlist-ids" to playlistIds.joinToString(","))
    )

    suspend fun likeTracks(vararg trackIds: Int) = requestPrimitiveForm<Revision>(
        "users",
        (me?.account?.uid ?: status().account.uid).toString(),
        "likes",
        "tracks",
        "add-multiple",
        method = HttpMethod.Post,
        body = hashMapOf("track-ids" to trackIds.joinToString(","))
    )

    suspend fun unlikeTracks(vararg trackIds: Int) = requestPrimitiveForm<Revision>(
        "users",
        (me?.account?.uid ?: status().account.uid).toString(),
        "likes",
        "tracks",
        "remove",
        method = HttpMethod.Post,
        body = hashMapOf("track-ids" to trackIds.joinToString(","))
    )
}


