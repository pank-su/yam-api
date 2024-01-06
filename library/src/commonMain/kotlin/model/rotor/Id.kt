package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class Id(val type: String, val tag: String)
