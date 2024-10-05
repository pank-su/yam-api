package landing.model

import kotlinx.serialization.Serializable
import su.pank.yamusic.landing.model.block.BlockType
import model.playlist.PlaylistId

@Serializable
data class LandingList(
    val type: BlockType,
    val typeForFrom: String,
    val title: String,
    val id: String? = null,
    val newReleases: List<Int> = listOf(),
    val newPlaylists: List<PlaylistId> = listOf(), val podcasts: List<Int> = listOf()
)
