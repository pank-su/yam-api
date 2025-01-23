package su.pank.yamapi.rotor.model

import kotlinx.serialization.Serializable

@Serializable
data class Restrictions(
    val language: Enumerate,
    val diversity: Enumerate,
    val mood: DiscreateScale? = null,
    val energy: DiscreateScale? = null,
    val moodEnergy: Enumerate? = null,
)


@Serializable
data class DiscreateScale(val type: String, val name: String, val min: DiscreteValue, val max: DiscreteValue)


@Serializable
data class DiscreteValue(val value: Int, val name: String)


@Serializable
data class Enumerate(val type: String, val name: String, val possibleValues: List<Value>)

@Serializable
data class Value(val value: String, val name: String)

