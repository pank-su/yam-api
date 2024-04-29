package model.rotor

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class Session(
    val radioSessionId: String,
    val batchId: String,
    val pumpkin: Boolean,
    val descriptionSeed: DescriptionSeed,
    val acceptedSeeds: List<DescriptionSeed>
) : Result()