package track.model

import kotlinx.serialization.Serializable
import su.pank.yamusic.track.model.TrackDTO

// TODO: to track
@Serializable
data class SimilarTracks(val track: TrackDTO? = null, val similarTracks: List<TrackDTO>)
