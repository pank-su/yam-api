package track.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fade(
    @SerialName("inStart")
    val inStart: Double,
    @SerialName("inStop")
    val inStop: Double,
    @SerialName("outStart")
    val outStart: Double,
    @SerialName("outStop")
    val outStop: Double
)