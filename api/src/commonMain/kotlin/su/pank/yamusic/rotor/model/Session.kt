package rotor.model

import kotlinx.serialization.Serializable
import su.pank.yamusic.rotor.model.DescriptionSeed

@Serializable
data class Session(
    val radioSessionId: String,
    val batchId: String,
    val pumpkin: Boolean,
    val descriptionSeed: DescriptionSeed,
    val acceptedSeeds: List<DescriptionSeed>
)


@Serializable
data class DescriptionSeed(val value: String, val tag: String, val type: String)
