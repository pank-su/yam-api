package su.pank.yamapi.downloadInfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

val SIGN_SALT = "XGRlBW9FXlekgbPrRHuSiA"

@Serializable
data class DownloadInfoData(
    val codec: Codec,
    val bitrateInKbps: Int,
    val gain: Boolean,
    val preview: Boolean,
    val downloadInfoUrl: String,
    val direct: Boolean,
    val container: Container? = null,
)

@Serializable
@XmlSerialName("download-info")
internal data class DownloadInfoXML(
    @XmlElement(true)
    val host: String,
    @XmlElement(true)
    val path: String,
    @XmlElement(true)
    val ts: String,
    @XmlElement(true)
    val region: Int,
    @XmlElement(true)
    val s: String,
)

@Serializable
enum class Codec {
    @SerialName("mp3")
    MP3,

    @SerialName("aac")
    AAC,

    @SerialName("flac")
    FLAC,
}

@Serializable
enum class Container {
    @SerialName("hls")
    HLS,
}
