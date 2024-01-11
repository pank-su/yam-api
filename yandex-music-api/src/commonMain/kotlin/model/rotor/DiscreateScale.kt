package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class DiscreateScale(val type: String, val name: String, val min: DiscreteValue, val max: DiscreteValue)
