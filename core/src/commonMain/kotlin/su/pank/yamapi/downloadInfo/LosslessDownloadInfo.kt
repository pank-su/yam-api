package su.pank.yamapi.downloadInfo


import su.pank.yamapi.YamApiClient
import su.pank.yamapi.downloadInfo.model.Codec
import su.pank.yamapi.downloadInfo.model.LosslessDownloadInfoData

/**
 * Представляет информацию о скачивании трека в lossless качестве с методами для получения прямой ссылки и скачивания.
 *
 * @param client Экземпляр YamApiClient, используемый для выполнения запросов к API.
 * @param losslessDownloadInfoData Объект LosslessDownloadInfoData, содержащий метаданные о скачивании.
 */
class LosslessDownloadInfo(
    private val client: YamApiClient,
    private val losslessDownloadInfoData: LosslessDownloadInfoData,
) {
    /** Качество аудиофайла. */
    val quality: String = losslessDownloadInfoData.quality

    /** Кодек аудиофайла. */
    val codec: Codec = losslessDownloadInfoData.codec

    /** Список URL для скачивания. */
    val urls: List<String> = losslessDownloadInfoData.urls

    /** Битрейт. */
    val bitrate: Int = losslessDownloadInfoData.bitrate







    override fun toString(): String =
        "LosslessDownloadInfo(quality='$quality', codec=$codec, urls=$urls, bitrate=$bitrate)"
}

