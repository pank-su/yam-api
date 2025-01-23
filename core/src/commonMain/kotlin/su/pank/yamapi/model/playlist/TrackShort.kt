package su.pank.yamapi.model.playlist

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import su.pank.yamapi.YaMusicApiClient
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.utils.IntOrStringSerializer

@Serializable
data class TrackShort(
    @Serializable(with = IntOrStringSerializer::class)
    val id: String,
    val timestamp: Instant
) {
    var track: TrackData? = null
    suspend fun fetchTrack(client: YaMusicApiClient): TrackData? {
        track = client.tracks(id)[0]
        return track
    }
}
