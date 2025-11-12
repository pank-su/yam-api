package su.pank.yamapi.track.model.lyrics

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import su.pank.yamapi.YamApiClient


/**
 * Текст песни.
 *
 * @param client Клиент YamApiClient.
 * @param downloadUrl URL для скачивания текста.
 * @param lyricId Идентификатор текста.
 * @param externalLyricId Внешний идентификатор текста.
 * @param writers Список авторов текста.
 * @param major Сервис откуда получен текст.
 */
class Lyrics(private val client: YamApiClient, lyricsData: LyricsData) {
    val downloadUrl: String = lyricsData.downloadUrl
    val lyricId: Int = lyricsData.lyricId
    val externalLyricId: String = lyricsData.externalLyricId
    val writers: List<String> = lyricsData.writers
    val major: LyricsMajor = lyricsData.major

    /**
     * Загружает текст песни с [downloadUrl].
     *
     * @return Текст песни в виде строки.
     */
    suspend fun fetch(): String {
        return client.httpClient.get(downloadUrl).bodyAsText()
    }


    override fun toString(): String {
        return "Lyrics(lyricId=$lyricId, externalLyricId=$externalLyricId, writers=$writers, major=$major, downloadUrl='$downloadUrl')"
    }

}