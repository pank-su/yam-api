package su.pank.yamapi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import su.pank.yamapi.account.AccountApi
import su.pank.yamapi.exceptions.ExperimentalYaMusicApi
import su.pank.yamapi.landing.LandingApi
import su.pank.yamapi.model.*
import su.pank.yamapi.model.album.Album
import su.pank.yamapi.model.search.*
import su.pank.yamapi.rotor.RotorApi
import su.pank.yamapi.track.TracksApi
import su.pank.yamapi.utils.setBody


abstract class YamClient {
    internal abstract val httpClient: HttpClient

    internal suspend inline fun <reified T> get(vararg path: String, block: HttpRequestBuilder.() -> Unit = {}): T {
        return httpClient.get {
            url {
                path(*path)
            }
            block()
        }.body()
    }

    internal suspend inline fun <reified T, reified R> get(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.get {
            url {
                path(*path)
                parameters {
                    setBody(body)
                }
            }
            block()
        }.body()
    }

    internal suspend inline fun <reified T, reified R> postForm(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.submitForm(
            formParameters = parameters { setBody(body) }
        ) {
            url {
                path(*path)
            }

            method = HttpMethod.Post
            block()
        }.body()
    }


    internal suspend inline fun <reified T, reified R> form(
        body: R,
        vararg path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return httpClient.submitForm(
            formParameters = parameters { setBody(body) }
        ) {
            url {
                path(*path)
            }

            method = HttpMethod.Get
            block()
        }.body()
    }

}


class YamApiClient(override val httpClient: HttpClient, val language: Language, val token: String?) : YamClient() {

    init {
        httpClient.config {
            defaultRequest {

                headers {
                    append("X-Yandex-Music-Client", "YandexMusicAndroid/24022571")
                    append("USER_AGENT", "Yandex-Music-API")
                    append("Accept-Language", language.toString())

                    if (token != null) {
                        append(HttpHeaders.Authorization, "OAuth $token")
                    }
                }
            }
        }
    }

    var jsonSettings = Json {
        ignoreUnknownKeys = true
    }

    val account: AccountApi = AccountApi(this)


    val landing: LandingApi = LandingApi(this)


    @ExperimentalYaMusicApi
    val rotor: RotorApi = RotorApi(this)

    val tracks: TracksApi = TracksApi(this)


    suspend fun genres(): List<Genre> = get("genres")

    suspend fun tags(tagId: String) = get<TagResult>("tags", tagId, "playlist-ids")


    suspend fun albumsWithTracks(albumId: Int) = get<Album>("albums", albumId.toString(), "with-tracks")

    suspend fun search(query: String, searchRequestBuilder: SearchRequestBuilder.() -> Unit) {


    }

    internal suspend fun search(searchRequest: SearchRequest): Search =
        get(
            searchRequest,
            "search"
        )
    suspend fun search(
        query: String,
        isCorrect: Boolean = false,
        type: QueryType = QueryType.All, // TODO: сделать выбор что искать с помощью типов, что может помочь сохранить типизацию
        page: Int = 0,
        playlistInBest: Boolean = false
    ) = search(SearchRequest(query, isCorrect, type, page, playlistInBest))

    suspend fun searchSuggest(part: String) = get<Suggestions>("search", "suggest") {
        parameter("part", part)
    }


    suspend fun userPlaylists(vararg kinds: Int, userId: Int? = null): List<Playlist> = postForm(
        hashMapOf("kinds" to kinds.joinToString(",")),
        "users",
        (userId).toString(),
        "playlists",
    )

    suspend fun userPlaylist(kind: Int, userId: Int? = null) = get<Playlist>(
        "users",
        (userId).toString(),
        "playlists",
        kind.toString()
    )


    // полное получение информации о пользователе
    suspend fun userInfo() = httpClient.get("https://login.yandex.ru/" + "info") {
        // TODO: token validation
        headers { append(HttpHeaders.Authorization, "OAuth $token") }
    }.body<UserInfo>()

    suspend fun playlistList(vararg playlistIds: String): List<Playlist> = postForm(
        hashMapOf("playlist-ids" to playlistIds.joinToString(",")),
        "playlists",
        "list",

        )
}


