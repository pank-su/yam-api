package model.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.track.Track


@kotlinx.serialization.Serializable
enum class EventType {
    @SerialName("type")
    Type,

    @SerialName("tracks")
    Tracks,

    @SerialName("artists")
    Artists,

    @SerialName("albums")
    Albums,

    @SerialName("notification")
    Notification
}

@Serializable
data class Event(
    val id: String,
    val type: EventType,
    val typeForFrom: String? = null,
    val title: String? = null,
    val tracks: List<Track>? = null
)
