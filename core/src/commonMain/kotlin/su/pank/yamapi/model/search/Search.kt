package su.pank.yamapi.model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Video
import model.album.Album
import model.artist.Artist
import model.playlist.Playlist
import su.pank.yamapi.account.model.User
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.track.model.supplement.Clip

@Serializable
data class Search(
    val searchRequestId: String,
    @SerialName("text")
    val query: String,
    val misspellResult: String? = null,
    val misspellOriginal: String? = null,
    @SerialName("nocorrect")
    val isCorrect: Boolean? = null,
    val misspellCorrected: Boolean? = null,
    val best: Best? = null,
    val artists: SearchResult<Artist>? = null,
    val albums: SearchResult<Album>? = null,
    val playlist: SearchResult<Playlist>? = null,
    val tracks: SearchResult<TrackData>? = null,
    val videos: SearchResult<Video>? = null,
    val users: SearchResult<User>? = null,
    val podcasts: SearchResult<Album>? = null,
    val podcastsEpisodes: SearchResult<TrackData>? = null,
    val clips: SearchResult<Clip>? = null,

    ) {
    var type: QueryType = QueryType.All
    internal var page: Int = 0


    suspend fun getPage(page: Int): Search =
        TODO("Добавить это в entity")// client!!.search(query = query, isCorrect = isCorrect ?: true, type, page)

    suspend fun nextPage(): Search = getPage(page + 1)

    suspend fun prevPage(): Search = getPage(page - 1)
}


@Serializable(with = BestSerializer::class)
data class Best(val type: QueryResponseType, val result: Any)



/**
 * Результат поиска различный от выбранного запроса. Этот dataclass упрощает обрабатывать ответ в [Search]
 */
@Serializable
data class SearchResult<T>(val total: Int, val perPage: Int, val order: Int, val results: List<T>)

enum class QueryType {
    All,
    Artist,
    User,
    Album,
    Playlist,
    Track,
    Podcast
}


