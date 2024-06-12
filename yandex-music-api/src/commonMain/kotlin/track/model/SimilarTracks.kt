package track.model

import kotlinx.serialization.Serializable

@Serializable
data class SimilarTracks(val track: Track? = null, val similarTracks: List<Track>)
