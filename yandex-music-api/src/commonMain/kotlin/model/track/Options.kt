package model.track

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Options {
    @SerialName("bookmate")
    Bookmate
}