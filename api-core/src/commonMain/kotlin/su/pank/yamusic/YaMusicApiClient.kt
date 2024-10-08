package su.pank.yamusic

import su.pank.yamusic.dsl.YandexMusicTagMaker
import su.pank.yamusic.exceptions.NotAuthenticatedException
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import model.*
import su.pank.yamusic.account.model.Status
import su.pank.yamusic.landing.LandingApi
import model.album.Album
import model.genre.Genre
import model.playlist.Playlist
import model.playlist.TagResult
import model.search.QueryType
import model.search.Search
import model.search.Suggestions
import su.pank.yamusic.rotor.RotorApi
import su.pank.yamusic.account.AccountApi
import su.pank.yamusic.builder.NetworkSettings
import su.pank.yamusic.exceptions.ExperimentalYaMusicApi
import su.pank.yamusic.track.TracksApi


@YandexMusicTagMaker
class YaMusicApiClient(val networkSettings: NetworkSettings, val language: Language, val token: String?) {

    val httpClient = networkSettings.httpClient

    val me: Status? = null // TODO: добавить что-то вроде авторизации

    var jsonSettings = Json {
        ignoreUnknownKeys = true
    }

    val account: AccountApi = AccountApi(this)


    val landing: LandingApi = LandingApi(this)


    @ExperimentalYaMusicApi
    val rotor: RotorApi = RotorApi(this)

    val tracks: TracksApi = TracksApi(this)


    suspend fun genres() = request<List<Genre>>("genres")

    suspend fun tags(tagId: String) = request<TagResult>("tags", tagId, "playlist-ids")


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

    private suspend fun usersSettings(): Nothing = TODO("с версии 0.1.5")

    suspend fun userPlaylists(vararg kinds: Int, userId: Int? = null) = requestForm<List<Playlist>>(
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


    // полное получение информации о пользователе
    suspend fun userInfo() = httpClient.get("https://login.yandex.ru/" + "info") {
        // TODO: token validation
        headers { append(HttpHeaders.Authorization, "OAuth $token") }
    }.body<UserInfo>()

    suspend fun playlistList(vararg playlistIds: String) = requestForm<List<Playlist>>(
        "playlists",
        "list",
        method = HttpMethod.Post,
        body = hashMapOf("playlist-ids" to playlistIds.joinToString(","))
    )


    suspend inline fun <reified T> HttpResponse.deserialize(): T {

        // escape all stupid chars
        val body = this.bodyAsText()/*.replace(Regex("\\\\[a-zA-ZА-Яа-я]+")) { "\\" + it.value }*/

        return jsonSettings.decodeFromString<T>(body)
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
        networkSettings.requestSettings(this)
        this.method = method
        url {
            appendPathSegments(components.toList())
        }
        headers {
            append("X-Yandex-Music-Client", "YandexMusicAndroid/24022571")
            append("USER_AGENT", "Yandex-Music-API")
            append("Accept-Language", language.toString())

            if (token != null) {
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
    internal suspend inline fun <reified T> request(
        components: List<String>,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.request(networkSettings.baseUrl) {
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
        }.deserialize<BasicResponse<T>>().result
    }

    /**
     * Запрос формы, который возвращает объект описанных моделей
     *
     * @param components компоненты ссылки
     * @param body тело запроса
     *
     * @see Result
     */
    internal suspend inline fun <reified T> requestForm(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.submitForm(
            networkSettings.baseUrl, formParameters =
                parameters {
                    body.forEach {
                        append(it.key, it.value)
                    }
                }) {
            networkSettings.requestSettings(this)
            basicSettings(method, components.toList())

        }.deserialize<BasicResponse<T>>().result
    }

    /**
     * Упрощение запроса request
     *
     * @see request
     */
    internal suspend inline fun <reified T> request(
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
    internal suspend inline fun <reified T> requestPost(
        vararg components: String,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return request(components.toList(), method = HttpMethod.Post, body)
    }


}


