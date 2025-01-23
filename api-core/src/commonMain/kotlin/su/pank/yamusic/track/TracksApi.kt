package su.pank.yamusic.track

import io.ktor.http.*
import io.ktor.util.*
import kotlinx.datetime.Clock
import model.Revision
import track.model.downloadInfo.DownloadInfo
import su.pank.yamusic.track.model.supplement.Supplement
import track.model.SimilarTracks
import su.pank.yamusic.track.model.TrackData
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import su.pank.yamusic.YaMusicApiClient

class TracksApi(private val client: YaMusicApiClient) {
    suspend operator fun invoke(vararg trackIds: String, withPositions: Boolean = true): List<TrackData> =
        client.requestForm(
            "tracks", method = HttpMethod.Post,
            body = hashMapOf("with-positions" to withPositions.toString(), "track-ids" to trackIds.joinToString(","))
        )

    suspend fun like(vararg trackIds: Int) = client.requestForm<Revision>(
        "users",
        (client.me?.account?.uid ?: client.account.status().account.uid).toString(),
        "likes",
        "tracks",
        "add-multiple",
        method = HttpMethod.Post,
        body = hashMapOf("track-ids" to trackIds.joinToString(","))
    )

    suspend fun unlike(vararg trackIds: Int) = client.requestForm<Revision>(
        "users",
        (client.me?.account?.uid ?: client.account.status().account.uid).toString(),
        "likes",
        "tracks",
        "remove",
        method = HttpMethod.Post,
        body = hashMapOf("track-ids" to trackIds.joinToString(","))
    )

    suspend fun similar(trackId: Int) = client.request<SimilarTracks>("tracks", trackId.toString(), "similar")

    suspend fun supplement(trackId: Int) = client.request<Supplement>("tracks", trackId.toString(), "supplement")


    suspend fun lyrics(trackId: Int, format: String = "TEXT"): Nothing =
        TODO("Необходима реализация get_sign_request")


    suspend fun downloadInfo(trackId: String) =
        client.request<List<DownloadInfo>>("tracks", trackId, "download-info")

    suspend fun downloadInfoNew(
        trackId: Int,
        canUseStreaming: Boolean = false
    ): List<DownloadInfo> {
        val timestamp = Clock.System.now().epochSeconds
        val hmacSign = HmacSHA256("p93jhgh689SBReK6ghtw62".encodeToByteArray()) // HmacSHA256()
        val sign = hmacSign.doFinal("${trackId}${timestamp}".encodeToByteArray()).encodeBase64()

        return client.request(
            listOf(
                "tracks",
                trackId.toString(),
                "download-info"
            ),
            method = HttpMethod.Get,
            body = hashMapOf(
                "can_use_streaming" to canUseStreaming.toString().lowercase(),
                "ts" to timestamp.toString(),
                "sign" to sign
            ),
        )

    }
}
