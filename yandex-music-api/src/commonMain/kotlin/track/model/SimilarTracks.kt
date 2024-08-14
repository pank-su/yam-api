package track.model

import kotlinx.serialization.Serializable

@Serializable
data class SimilarTracks(val track: TrackDTO? = null, val similarTracks: List<TrackDTO>)
