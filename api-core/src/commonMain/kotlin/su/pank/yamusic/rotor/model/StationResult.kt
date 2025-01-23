package su.pank.yamusic.rotor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rotor.model.Station

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
)

@Serializable
data class RotorSettings(
    val language: RotorLanguage,
    val diversity: RotorDiversity,
    @Deprecated("Устарело", ReplaceWith("moodEnergy")) val mood: Int? = null,
    val energy: Int? = null,
    val moodEnergy: RotorMoodEnergy? = null
)


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


@Serializable
enum class RotorLanguage {
    @SerialName("not-russian")
    NotRussian,

    @SerialName("russian")
    Russian,

    @SerialName("any")
    Any
}


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
