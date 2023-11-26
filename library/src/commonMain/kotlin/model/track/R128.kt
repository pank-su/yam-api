package model.track


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class R128(
    @SerialName("i")
    val i: Double,
    @SerialName("tp")
    val tp: Double
)