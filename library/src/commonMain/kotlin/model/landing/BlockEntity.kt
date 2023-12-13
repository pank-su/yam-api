package model.landing

import kotlinx.serialization.Serializable
import model.feed.GeneratedPlaylist


@Serializable
abstract class BlockEntity {
    abstract val id: String
    abstract val type: BlockType
}

@Serializable
data class GeneratedEntity(override val id: String, override val type: BlockType, val data: GeneratedPlaylist) :
    BlockEntity()


@Serializable
data class PromotionEntity(override val id: String, override val type: BlockType, val data: Promotion) : BlockEntity()
