package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionSeed(val value: String, val tag: String, val type: String)
