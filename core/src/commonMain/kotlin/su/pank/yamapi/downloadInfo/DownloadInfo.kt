package su.pank.yamapi.downloadInfo

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import nl.adaptivity.xmlutil.serialization.XML
import org.kotlincrypto.hash.md.MD5
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.downloadInfo.model.Codec
import su.pank.yamapi.downloadInfo.model.Container
import su.pank.yamapi.downloadInfo.model.DownloadInfoData
import su.pank.yamapi.downloadInfo.model.DownloadInfoXML
import su.pank.yamapi.downloadInfo.model.SIGN_SALT

/**
 * Представляет информацию о скачивании трека с методами для получения прямой ссылки и скачивания.
 *
 * @param client Экземпляр YamApiClient, используемый для выполнения запросов к API.
 * @param downloadInfoData Объект DownloadInfoData, содержащий метаданные о скачивании.
 */
class DownloadInfo(
    private val client: YamApiClient,
    private val downloadInfoData: DownloadInfoData,
) {
    /** Кодек аудиофайла. */
    val codec: Codec = downloadInfoData.codec

    /** Битрейт в Kbps. */
    val bitrateInKbps: Int = downloadInfoData.bitrateInKbps

    /** Применено ли усиление громкости. */
    val gain: Boolean = downloadInfoData.gain

    /** Является ли превью. */
    val preview: Boolean = downloadInfoData.preview

    /** URL для получения информации о скачивании. */
    val downloadInfoUrl: String = downloadInfoData.downloadInfoUrl

    /** Доступна ли прямая ссылка. */
    val direct: Boolean = downloadInfoData.direct

    /** Контейнер файла. */
    val container: Container? = downloadInfoData.container


    @OptIn(ExperimentalStdlibApi::class)
    private fun buildDirectLink(xml: String): String {
        val info = XML { autoPolymorphic = true }.decodeFromString(DownloadInfoXML.serializer(), xml)
        val sign = MD5().digest((SIGN_SALT + info.path.substring(1) + info.s).toByteArray(Charsets.UTF_8)).toHexString()

        return "https://${info.host}/get-mp3/$sign/${info.ts}${info.path}"
    }

    /**
     * Получает прямую ссылку на скачивание.
     *
     * @return Прямая ссылка или null, если не удалось получить.
     */
    suspend fun fetchDirectLink(): String? {
        val xml =
            client.httpClient
                .get(downloadInfoUrl) {
                    headers {
                        // append(HttpHeaders.Authorization, "OAuth ${client.token}")
                    }
                }.body() as String
        return buildDirectLink(xml)
    }

    /**
     * Скачивает аудиофайл.
     *
     * @return Массив байтов аудиофайла.
     */
    suspend fun download(): ByteArray {
        val link = fetchDirectLink()
        return client.httpClient.get(link!!).body() as ByteArray
    }

    override fun toString(): String =
        "DownloadInfo(codec=$codec, bitrateInKbps=$bitrateInKbps, gain=$gain, preview=$preview, downloadInfoUrl='$downloadInfoUrl', direct=$direct, container=$container)"
}
