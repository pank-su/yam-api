package model.playlist

import kotlinx.serialization.Serializable

@Serializable
data class Tag(val id: String, val value: String, val name: String, val ogDescription: String)
