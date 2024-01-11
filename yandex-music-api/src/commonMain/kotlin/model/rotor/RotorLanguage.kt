package model.rotor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RotorLanguage {
    @SerialName("not-russian")
    NotRussian,

    @SerialName("russian")
    Russian,

    @SerialName("any")
    Any
}
