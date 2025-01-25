package su.pank.yamapi.track.model.supplement

import kotlinx.serialization.Serializable
import su.pank.yamapi.model.Artist

@Serializable
data class Clip(
    val clipId: Int,
    val title: String,
    val playerId: String,
    val uuid: String,
    val thumbnail: String,
    val previewUrl: String,
    val duration: Int,
    val trackIds: List<Int>? = null,
    val artists: List<Artist>,
    val explicit: Boolean
)
