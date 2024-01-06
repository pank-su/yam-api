package model.search

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
    val text: String,
    val misspellResult: String? = null,
    val misspellOriginal: String? = null,
    val nocorrect: Boolean? = null,
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
    var page: Int = 0
}
