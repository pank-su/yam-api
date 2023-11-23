package model.ad

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Feature {
    @SerialName("basic-plus")
    BasicPlus,

    @SerialName("basic-music")
    BasicMusic,

    @SerialName("basic-kinopoisk")
    BasicKinopoisk,
}