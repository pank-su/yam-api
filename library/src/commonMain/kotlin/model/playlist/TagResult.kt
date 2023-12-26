package model.playlist

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class TagResult(val tag: Tag, val ids: List<PlaylistId>) : Result()
