package model.playlist

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistId(val uid: Int, val kind: Int)