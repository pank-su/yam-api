package su.pank.yamapi.downloadInfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LosslessDownloadInfoData(
    val quality: String,
    val codec: Codec,
    val urls: List<String>,
    val bitrate: Int,
)

@Serializable
data class LosslessDownloadInfoRequest(
    @SerialName("ts") val timestamp: Long,
    val trackId: String,
    val quality: String = "lossless",
    val codecs: String = "flac,aac,he-aac,mp3",
    val transports: String = "raw",
    val sign: String,
    )