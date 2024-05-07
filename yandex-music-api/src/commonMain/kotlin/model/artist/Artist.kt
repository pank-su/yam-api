package model.artist

import kotlinx.serialization.Serializable
import model.cover.Cover

@Serializable
data class Artist(
    val id: Int,
    @Serializable(with = ArtistNameSerializer::class)
    val name: String, val cover: Cover? = null,
    val various: Boolean? = null,
    val composer: Boolean? = null,
    val genres: List<String>? = null
)
