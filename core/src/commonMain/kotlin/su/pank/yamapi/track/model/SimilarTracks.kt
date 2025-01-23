package track.model

import kotlinx.serialization.Serializable
import su.pank.yamapi.track.model.TrackData

// TODO: to track
@Serializable
data class SimilarTracks(val track: TrackData? = null, val similarTracks: List<TrackData>)
