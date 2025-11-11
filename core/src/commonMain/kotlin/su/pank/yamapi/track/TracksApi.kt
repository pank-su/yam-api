@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.track

import io.ktor.util.*
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.downloadInfo.DownloadInfo
import su.pank.yamapi.downloadInfo.LosslessDownloadInfo
import su.pank.yamapi.downloadInfo.model.DownloadInfoData
import su.pank.yamapi.downloadInfo.model.LosslessDownloadInfoData
import su.pank.yamapi.downloadInfo.model.LosslessDownloadInfoRequest
import su.pank.yamapi.model.Revision
import su.pank.yamapi.track.model.Sign
import su.pank.yamapi.track.model.SimilarTracks
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.track.model.lyrics.Lyrics
import su.pank.yamapi.track.model.lyrics.LyricsData
import su.pank.yamapi.track.model.lyrics.LyricsFormat
import su.pank.yamapi.track.model.lyrics.LyricsRequest
import su.pank.yamapi.track.model.supplement.Supplement
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * API для работы с треками.
 *
 * @param client Клиент YamApiClient.
 */
class TracksApi(
    private val client: YamApiClient,
) {

    private val SIGN = HmacSHA256("p93jhgh689SBReK6ghtw62".encodeToByteArray())

    /**
     * Получает треки по идентификаторам.
     *
     * @param trackIds Идентификаторы треков.
     * @param withPositions Включать ли позиции.
     * @return Список треков.
     */
    suspend operator fun invoke(
        vararg trackIds: String,
        withPositions: Boolean = true,
    ): List<Track> =
        client
            .postForm<List<TrackData>, Map<String, String>>(
                hashMapOf("with-positions" to withPositions.toString(), "track-ids" to trackIds.joinToString(",")),
                "tracks",
            ).map { Track(client, it) }

    /**
     * Лайкает треки.
     *
     * @param trackIds Идентификаторы треков.
     * @return Ревизия.
     */
    suspend fun like(vararg trackIds: String): Revision =
        client.postForm(
            hashMapOf("track-ids" to trackIds.joinToString(",")),
            "users",
            client.resolveUserId(),
            "likes",
            "tracks",
            "add-multiple",
        )

    /**
     * Убирает лайк с треков.
     *
     * @param trackIds Идентификаторы треков.
     * @return Ревизия.
     */
    suspend fun unlike(vararg trackIds: String): Revision =
        client.postForm(
            hashMapOf("track-ids" to trackIds.joinToString(",")),
            "users",
            (client.resolveUserId()),
            "likes",
            "tracks",
            "remove",
        )

    /**
     * Получает похожие треки.
     *
     * @param trackId Идентификатор трека.
     * @return Похожие треки.
     */
    suspend fun similar(trackId: String) = client.get<SimilarTracks>("tracks", trackId, "similar")

    /**
     * Получает дополнение к треку.
     *
     * @param trackId Идентификатор трека.
     * @return Дополнение.
     */
    suspend fun supplement(trackId: String) = client.get<Supplement>("tracks", trackId, "supplement")

    /**
     * Получает информацию о скачивании.
     *
     * @param trackId Идентификатор трека.
     * @return Информация о скачивании.
     */
    suspend fun downloadInfo(trackId: String) =
        client
            .get<List<DownloadInfoData>>("tracks", trackId, "download-info")
            .map { DownloadInfo(client, it) }

    /**
     * Получает информацию о скачивании (новый метод).
     *
     * @param trackId Идентификатор трека.
     * @param canUseStreaming Можно ли использовать стриминг.
     * @return Информация о скачивании.
     */
    suspend fun downloadInfoNew(
        trackId: String,
        canUseStreaming: Boolean = false,
    ): List<DownloadInfo> {
        val sign = signTrack(trackId)

        return client
            .form<List<DownloadInfoData>, HashMap<String, String>>(
                hashMapOf(
                    "can_use_streaming" to canUseStreaming.toString().lowercase(),
                    "ts" to sign.timestamp.toString(),
                    "sign" to sign.value,
                ),
                "tracks",
                trackId.toString(),
                "download-info",
            ).map { DownloadInfo(client, it) }
    }

    fun signTrack(trackId: String): Sign {
        val timestamp = Clock.System.now().epochSeconds

        return signData("${trackId}$timestamp", timestamp)
    }

    fun signData(data: String, timestamp: Long, sign: HmacSHA256 = SIGN): Sign = Sign(timestamp,
        sign.doFinal(data.encodeToByteArray()).encodeBase64())


    suspend fun lyrics(
        trackId: String,
        format: LyricsFormat = LyricsFormat.TEXT,
    ): Lyrics {
        val sign = signTrack(trackId)
        val body = LyricsRequest(format, sign.timestamp, sign.value)

        return client.get<LyricsData, LyricsRequest>(body = body, "tracks", trackId, "lyrics").let {
            Lyrics(client, it)
        }
    }

    suspend fun losslessInfo(trackId: String): LosslessDownloadInfo {
        val timestamp = Clock.System.now().epochSeconds
        val sign = signData("${timestamp}${trackId}losslessflacaache-aacmp3raw", timestamp)

        val body = LosslessDownloadInfoRequest(sign.timestamp, trackId, sign = sign.value.dropLast(1))
        return client.get<HashMap<String, LosslessDownloadInfoData>, LosslessDownloadInfoRequest>(body = body, "get-file-info").let {
            LosslessDownloadInfo(client, it.get("downloadInfo")!!)
        }
    }
}
