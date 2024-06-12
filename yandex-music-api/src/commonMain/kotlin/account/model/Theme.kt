package account.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Theme {
    @SerialName("white")
    White,

    @SerialName("black")
    Black
}