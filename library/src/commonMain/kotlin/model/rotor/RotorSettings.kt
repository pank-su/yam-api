package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class RotorSettings(
    val language: RotorLanguage,
    val diversity: RotorDiversity,
    @Deprecated("Устарело", ReplaceWith("moodEnergy")) val mood: Int? = null,
    val energy: Int? = null,
    val moodEnergy: RotorMoodEnergy? = null
)
