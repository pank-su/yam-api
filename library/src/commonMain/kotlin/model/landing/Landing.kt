package model.landing

import kotlinx.serialization.Serializable
import model.Result


@Serializable
data class Landing(
    val pumpkin: Boolean,
    val contentId: String,
    val blocks: List<Block>
) : Result()