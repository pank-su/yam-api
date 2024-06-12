package model.playlist

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import track.model.Track
import utils.IntOrStringSerializer

@Serializable
data class TrackShort(
    @Serializable(with = IntOrStringSerializer::class)
    val id: String,
    val timestamp: Instant
) {
    var track: Track? = null
    suspend fun fetchTrack(client: Client): Track? {
        track = client.tracks(id)[0]
        return track
    }
}
