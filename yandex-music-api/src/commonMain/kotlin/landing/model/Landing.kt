package landing.model

import kotlinx.serialization.Serializable
import landing.model.block.Block


@Serializable
data class Landing(
    val pumpkin: Boolean,
    val contentId: String,
    val blocks: List<Block>
)