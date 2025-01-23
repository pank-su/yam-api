package su.pank.yamusic.rotor.model

import kotlinx.serialization.Serializable

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
