package model.playlist

import kotlinx.serialization.Serializable

@Serializable
data class PlayCounter(val value: UInt, val description: String, val updated: Boolean)
