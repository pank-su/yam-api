package model.track

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import utils.IntOrStringSerializer

@Serializable
data class TrackShort(
    @Serializable(with = IntOrStringSerializer::class)
    val id: String,
    val timestamp: Instant
) {
    var track: Track? = null
    suspend fun fetchTrack(client: Client): Track? {
        track = client.tracks(id).value?.get(0)
        return track
    }
}
