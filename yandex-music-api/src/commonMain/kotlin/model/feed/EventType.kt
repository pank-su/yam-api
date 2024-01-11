package model.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
    Notification,

    @SerialName("social-tracks")
    SocialTracks
}