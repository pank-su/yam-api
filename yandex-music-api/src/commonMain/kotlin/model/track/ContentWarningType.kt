package model.track

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ContentWarningType {
    @SerialName("explicit")
    Explicit
}