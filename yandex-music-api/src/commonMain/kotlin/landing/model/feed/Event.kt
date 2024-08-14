package landing.model.feed

import kotlinx.serialization.Serializable
import track.model.TrackDTO


@Serializable
data class Event(
    val id: String,
    val type: EventType,
    val typeForFrom: String? = null,
    val title: String? = null,
    val tracks: List<TrackDTO>? = null
)
