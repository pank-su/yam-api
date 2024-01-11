package model.account

import kotlinx.serialization.Serializable

@Serializable
data class Button(
    val text: String,
    val bgColor: String,
    val textColor: String,
    val uri: String
)
