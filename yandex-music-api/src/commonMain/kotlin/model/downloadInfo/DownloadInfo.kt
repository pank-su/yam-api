package model.downloadInfo

import Client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML
import org.kotlincrypto.hash.md.MD5

val SIGN_SALT = "XGRlBW9FXlekgbPrRHuSiA"


@Serializable
data class DownloadInfo(
    val codec: Codec,
    val bitrateInKbps: Int,
    val gain: Boolean,
    val preview: Boolean,
    val downloadInfoUrl: String,
    val direct: Boolean
) {
    var directLink: String? = null;
    @OptIn(ExperimentalStdlibApi::class)
    private fun buildDirectLink(xml: String): String {
        val info = XML { autoPolymorphic = true }.decodeFromString(DownloadInfoXML.serializer(), xml)
        val sign = MD5().digest((SIGN_SALT + info.path.substring(1) + info.s).toByteArray(Charsets.UTF_8)).toHexString()

        return "https://${info.host}/get-mp3/${sign}/${info.ts}${info.path}"
    }

    suspend fun fetchDirectLink(client: Client): String? {
        val xml = client.httpClient.get(downloadInfoUrl) {
            headers {
                append(HttpHeaders.Authorization, "OAuth ${client.token}")
            }
        }.body() as String
        directLink = buildDirectLink(xml)
        return directLink
    }

    suspend fun download(client: Client): ByteArray {
        val link = directLink ?: fetchDirectLink(client)
        return client.httpClient.get(link!!).body() as ByteArray
    }
}
