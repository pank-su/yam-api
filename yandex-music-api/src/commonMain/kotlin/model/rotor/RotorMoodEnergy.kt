package model.rotor

import kotlinx.serialization.SerialName

enum class RotorMoodEnergy {
    @SerialName("fun")
    Fun,

    @SerialName("active")
    Active,

    @SerialName("calm")
    Calm,

    @SerialName("sad")
    Sad,

    @SerialName("all")
    All
}
