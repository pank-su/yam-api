package rotor.model

import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    val language: Enumerate,
    val diversity: Enumerate,
    val mood: DiscreateScale? = null,
    val energy: DiscreateScale? = null,
    val moodEnergy: Enumerate? = null,
)
