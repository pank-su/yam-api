package model.rotor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class StationResult(
    val rupTitle: String,
    val rupDescription: String,
    val station: Station,
    val settings: RotorSettings,
    val settings2: RotorSettings? = null,
    val adParams: AdParams? = null,
    val explanation: String? = null,
    @SerialName("prerolls") val preRolls: List<String>? = null,
    val customName: String? = null
) : Result()