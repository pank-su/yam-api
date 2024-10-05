package model.album

import kotlinx.serialization.Serializable

@Serializable
data class TrackPosition(val volume: Int, val index: Int)
