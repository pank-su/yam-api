package landing.model.block

import kotlinx.serialization.Serializable

@Serializable
data class Block(
    val id: String,
    val type: BlockType,
    val typeForFrom: BlockType,
    val title: String,
    val entities: List<BlockEntity>
)
