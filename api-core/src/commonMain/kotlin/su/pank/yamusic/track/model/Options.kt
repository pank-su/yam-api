package track.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Options {
    @SerialName("bookmate")
    Bookmate
}