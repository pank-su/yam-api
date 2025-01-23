package track.model.downloadInfo

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import org.kotlincrypto.hash.md.MD5
import su.pank.yamapi.YaMusicApiClient

val SIGN_SALT = "XGRlBW9FXlekgbPrRHuSiA"


@Serializable
data class DownloadInfo(
    val codec: Codec,
    val bitrateInKbps: Int,
    val gain: Boolean,
    val preview: Boolean,
    val downloadInfoUrl: String,
    val direct: Boolean,
    val container: Container? = null
) {
    var directLink: String? = null

    @OptIn(ExperimentalStdlibApi::class)
    private fun buildDirectLink(xml: String): String {
        val info = XML { autoPolymorphic = true }.decodeFromString(DownloadInfoXML.serializer(), xml)
        val sign = MD5().digest((SIGN_SALT + info.path.substring(1) + info.s).toByteArray(Charsets.UTF_8)).toHexString()

        return "https://${info.host}/get-mp3/${sign}/${info.ts}${info.path}"
    }

    suspend fun fetchDirectLink(client: YaMusicApiClient): String? {
        if (directLink != null) return directLink
        val xml = client.httpClient.get(downloadInfoUrl) {
            headers {
                append(HttpHeaders.Authorization, "OAuth ${client.token}")
            }
        }.body() as String
        directLink = buildDirectLink(xml)
        return directLink
    }

    suspend fun download(client: YaMusicApiClient): ByteArray {
        val link = directLink ?: fetchDirectLink(client)
        return client.httpClient.get(link!!).body() as ByteArray
    }
}


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
    val s: String
)


@Serializable
enum class Codec {
    @SerialName("mp3")
    MP3,

    @SerialName("aac")
    AAC,

    @SerialName("flac")
    FLAC
}

@Serializable
enum class Container {
    @SerialName("hls")
    HLS
}

