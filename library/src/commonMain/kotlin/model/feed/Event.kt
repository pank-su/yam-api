package model.feed

import kotlinx.serialization.Serializable
import model.track.Track


@Serializable
data class Event(
    val id: String,
    val type: EventType,
    val typeForFrom: String? = null,
    val title: String? = null,
    val tracks: List<Track>? = null
)
