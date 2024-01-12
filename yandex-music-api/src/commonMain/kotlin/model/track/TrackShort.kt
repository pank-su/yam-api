package model.track

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TrackShort(
    val id: Int,
    val timestamp: Instant
) {
    var track: Track? = null
    suspend fun fetchTrack(client: Client): Track? {
        track = client.tracks(id).value?.get(0)
        return track
    }
}
