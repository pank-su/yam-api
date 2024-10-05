package model.playlist

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import su.pank.yamusic.track.model.TrackDTO
import su.pank.yamusic.utils.IntOrStringSerializer

@Serializable
data class TrackShort(
    @Serializable(with = IntOrStringSerializer::class)
    val id: String,
    val timestamp: Instant
) {
    var track: TrackDTO? = null
    suspend fun fetchTrack(client: Client): TrackDTO? {
        track = client.tracks(id)[0]
        return track
    }
}
