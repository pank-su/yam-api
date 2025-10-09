@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.track

import io.ktor.util.*
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.model.Revision
import su.pank.yamapi.track.model.DownloadInfo
import su.pank.yamapi.track.model.SimilarTracks
import su.pank.yamapi.track.model.TrackData
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
    suspend fun like(vararg trackIds: Int): Revision =
        client.postForm(
            hashMapOf("track-ids" to trackIds.joinToString(",")),
            "users",
            (
                    null ?: client.account
                        .status()
                        .account.uid
                    ).toString(), // FIXME
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
    suspend fun unlike(vararg trackIds: Int): Revision =
        client.postForm(
            hashMapOf("track-ids" to trackIds.joinToString(",")),
            "users",
            (
                    null ?: client.account
                        .status()
                        .account.uid
                    ).toString(), // fixme
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
    suspend fun similar(trackId: Int) = client.get<SimilarTracks>("tracks", trackId.toString(), "similar")

    /**
     * Получает дополнение к треку.
     *
     * @param trackId Идентификатор трека.
     * @return Дополнение.
     */
    suspend fun supplement(trackId: Int) = client.get<Supplement>("tracks", trackId.toString(), "supplement")

    /**
     * Получает текст песни.
     *
     * @param trackId Идентификатор трека.
     * @param format Формат.
     * @return Текст.
     */
    private suspend fun lyrics(
        trackId: Int,
        format: String = "TEXT",
    ): Nothing = TODO("Необходима реализация get_sign_request")

    /**
     * Получает информацию о скачивании.
     *
     * @param trackId Идентификатор трека.
     * @return Информация о скачивании.
     */
    suspend fun downloadInfo(trackId: String) = client.get<List<DownloadInfo>>("tracks", trackId, "download-info")

    /**
     * Получает информацию о скачивании (новый метод).
     *
     * @param trackId Идентификатор трека.
     * @param canUseStreaming Можно ли использовать стриминг.
     * @return Информация о скачивании.
     */
    suspend fun downloadInfoNew(
        trackId: Int,
        canUseStreaming: Boolean = false,
    ): List<DownloadInfo> {
        val timestamp = Clock.System.now().epochSeconds
        val hmacSign = HmacSHA256("p93jhgh689SBReK6ghtw62".encodeToByteArray()) // HmacSHA256()
        val sign = hmacSign.doFinal("${trackId}$timestamp".encodeToByteArray()).encodeBase64()

        return client.form(
            hashMapOf(
                "can_use_streaming" to canUseStreaming.toString().lowercase(),
                "ts" to timestamp.toString(),
                "sign" to sign,
            ),
            "tracks",
            trackId.toString(),
            "download-info",
        )
    }
}
