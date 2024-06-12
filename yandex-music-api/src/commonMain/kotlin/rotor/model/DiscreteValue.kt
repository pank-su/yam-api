package rotor.model

import kotlinx.serialization.Serializable

@Serializable
data class DiscreteValue(val value: Int, val name: String)
