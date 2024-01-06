package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class Enumerate(val type: String, val name: String, val possibleValues: List<Value>)
