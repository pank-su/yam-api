package model.track

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class SimilarTracks(val track: Track? = null, val similarTracks: List<Track>) : Result()
