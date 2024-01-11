package model.artist

import kotlinx.serialization.Serializable
import model.playlist.Cover

@Serializable
data class Artist(
    val id: Int, val name: String, val cover: Cover? = null,
    val various: Boolean,
    val composer: Boolean,
    val genres: List<String>
)
