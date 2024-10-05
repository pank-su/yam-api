package rotor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RotorDiversity {
    @SerialName("favorite")
    Favorite,

    @SerialName("popular")
    Popular,

    @SerialName("discover")
    Discover,

    @SerialName("default")
    Default
}
