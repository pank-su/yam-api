package su.pank.yamapi.track

import io.ktor.util.*
import kotlinx.datetime.Clock
import model.Revision
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.track.model.supplement.Supplement
import track.model.SimilarTracks
import track.model.downloadInfo.DownloadInfo

class TracksApi(private val client: YamApiClient) {
    suspend operator fun invoke(vararg trackIds: String, withPositions: Boolean = true): List<TrackData> =
        client.postForm(
            hashMapOf("with-positions" to withPositions.toString(), "track-ids" to trackIds.joinToString(",")),
            "tracks",
        )


    suspend fun like(vararg trackIds: Int): Revision = client.postForm(
        hashMapOf("track-ids" to trackIds.joinToString(",")),
        "users",
        (null ?: client.account.status().account.uid).toString(), // FIXME
        "likes",
        "tracks",
        "add-multiple",
    )


    suspend fun unlike(vararg trackIds: Int): Revision = client.postForm(
        hashMapOf("track-ids" to trackIds.joinToString(",")),
        "users",
        (null ?: client.account.status().account.uid).toString(), // fixme
        "likes",
        "tracks",
        "remove",
    )

    suspend fun similar(trackId: Int) = client.get<SimilarTracks>("tracks", trackId.toString(), "similar")

    suspend fun supplement(trackId: Int) = client.get<Supplement>("tracks", trackId.toString(), "supplement")


    suspend fun lyrics(trackId: Int, format: String = "TEXT"): Nothing =
        TODO("Необходима реализация get_sign_request")


    suspend fun downloadInfo(trackId: String) =
        client.get<List<DownloadInfo>>("tracks", trackId, "download-info")

    suspend fun downloadInfoNew(
        trackId: Int,
        canUseStreaming: Boolean = false
    ): List<DownloadInfo> {
        val timestamp = Clock.System.now().epochSeconds
        val hmacSign = HmacSHA256("p93jhgh689SBReK6ghtw62".encodeToByteArray()) // HmacSHA256()
        val sign = hmacSign.doFinal("${trackId}${timestamp}".encodeToByteArray()).encodeBase64()

        return client.form(
            hashMapOf(
                "can_use_streaming" to canUseStreaming.toString().lowercase(),
                "ts" to timestamp.toString(),
                "sign" to sign
            ),
            "tracks",
            trackId.toString(),
            "download-info"

        )

    }
}
