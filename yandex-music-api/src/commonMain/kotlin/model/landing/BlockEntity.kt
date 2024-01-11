package model.landing

import kotlinx.serialization.Serializable


@Serializable(with = BlockSerializer::class)
data class BlockEntity(
    val id: String,
    val type: BlockType,
    val data: Any
)

