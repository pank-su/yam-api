package model.rotor

import kotlinx.serialization.Serializable

@Serializable
data class StationResult(val station: Station, val settings: RotorSettings)