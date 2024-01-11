package model.track

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TrackShort(
    val id: Int,
    val timestamp: Instant
) {
    suspend fun fetchTrack(client: Client) = client.tracks(id).value?.get(0)

}
