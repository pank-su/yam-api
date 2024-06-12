package landing.model.feed

import kotlinx.serialization.Serializable
import model.playlist.Playlist

@Serializable
data class GeneratedPlaylist(
    val type: GeneratedPlaylistType,
    val ready: Boolean,
    val notify: Boolean,
    val data: Playlist
)