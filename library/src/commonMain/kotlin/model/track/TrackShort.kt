package model.track

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TrackShort(
    val id: Int,
    val timestamp: Instant
)
