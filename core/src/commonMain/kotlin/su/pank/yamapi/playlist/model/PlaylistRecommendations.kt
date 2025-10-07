package su.pank.yamapi.playlist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.yamapi.track.model.TrackData

@Serializable
data class PlaylistRecommendations(
    val tracks: List<TrackData> = emptyList(),
    @SerialName("batchId")
    val batchId: String? = null,
)
