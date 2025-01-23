package su.pank.yamusic.model.playlist

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import su.pank.yamusic.YaMusicApiClient
import su.pank.yamusic.track.model.TrackData
import su.pank.yamusic.utils.IntOrStringSerializer

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
