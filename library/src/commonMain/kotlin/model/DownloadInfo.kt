package model

import kotlinx.serialization.Serializable

@Serializable
data class DownloadInfo(
    val codec: String,
    val bitrateInKbps: Int,
    val gain: Boolean,
    val preview: Boolean,
    val downloadInfoUrl: String,
    val direct: Boolean
)
