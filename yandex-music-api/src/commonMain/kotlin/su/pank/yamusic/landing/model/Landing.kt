package landing.model

import kotlinx.serialization.Serializable
import su.pank.yamusic.landing.model.block.Block


@Serializable
data class Landing(
    val pumpkin: Boolean,
    val contentId: String,
    val blocks: List<Block>
)