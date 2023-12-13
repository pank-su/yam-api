package model.landing

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Block(
    val id: String,
    val type: BlockType,
    val typeForFrom: BlockType,
    val title: String,
    val entities: List<JsonObject> // TODO
)
