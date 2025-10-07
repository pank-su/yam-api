package su.pank.yamapi.track.model

import kotlinx.serialization.Serializable
import su.pank.yamapi.playlist.model.TrackShort

@Serializable
data class TracksList(
    val uid: Int,
    val revision: Int,
    val tracks: List<TrackShort> = emptyList(),
)
