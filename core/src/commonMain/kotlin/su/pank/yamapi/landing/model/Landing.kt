package su.pank.yamapi.landing.model

import kotlinx.serialization.Serializable
import model.playlist.PlaylistId


@Serializable
data class Landing(
    val pumpkin: Boolean,
    val contentId: String,
    val blocks: List<Block>
)

@Serializable
data class LandingList(
    val type: BlockType,
    val typeForFrom: String,
    val title: String,
    val id: String? = null,
    val newReleases: List<Int> = listOf(),
    val newPlaylists: List<PlaylistId> = listOf(), val podcasts: List<Int> = listOf()
)
