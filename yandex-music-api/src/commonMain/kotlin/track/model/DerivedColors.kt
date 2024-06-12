package track.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DerivedColors(
    @SerialName("accent")
    val accent: String,
    @SerialName("average")
    val average: String,
    @SerialName("miniPlayer")
    val miniPlayer: String,
    @SerialName("waveText")
    val waveText: String
)