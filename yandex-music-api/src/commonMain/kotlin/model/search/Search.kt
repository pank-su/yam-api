package model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Video
import account.model.User
import model.album.Album
import model.artist.Artist
import model.playlist.Playlist
import track.model.supplement.Clip
import track.model.TrackDTO

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
    val tracks: SearchResult<TrackDTO>? = null,
    val videos: SearchResult<Video>? = null,
    val users: SearchResult<User>? = null,
    val podcasts: SearchResult<Album>? = null,
    val podcastsEpisodes: SearchResult<TrackDTO>? = null,
    val clips: SearchResult<Clip>? = null,

    )  {
    var type: QueryType = QueryType.All
    internal var page: Int = 0

    suspend fun getPage(page: Int): Search = TODO("Добавить это в entity")// client!!.search(query = query, isCorrect = isCorrect ?: true, type, page)
    suspend fun nextPage(): Search = getPage(page + 1)

    suspend fun prevPage(): Search = getPage(page - 1)
}
