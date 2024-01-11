package model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result
import model.Video
import model.account.User
import model.album.Album
import model.artist.Artist
import model.playlist.Playlist
import model.supplement.Clip
import model.track.Track

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
    val tracks: SearchResult<Track>? = null,
    val videos: SearchResult<Video>? = null,
    val users: SearchResult<User>? = null,
    val podcasts: SearchResult<Album>? = null,
    val podcastsEpisodes: SearchResult<Track>? = null,
    val clips: SearchResult<Clip>? = null,

    ) : Result() {
    var type: QueryType = QueryType.All
    internal var page: Int = 0

    suspend fun getPage(page: Int): Search = client!!.search(query = query, isCorrect = isCorrect ?: true, type, page)
    suspend fun nextPage(): Search = getPage(page + 1)

    suspend fun prevPage(): Search = getPage(page - 1)
}
