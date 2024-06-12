package model.playlist

import kotlinx.serialization.Serializable

@Serializable
data class TagResult(val tag: Tag, val ids: List<PlaylistId>)
