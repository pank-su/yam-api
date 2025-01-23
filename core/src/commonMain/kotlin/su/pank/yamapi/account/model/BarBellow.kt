package su.pank.yamapi.account.model

import kotlinx.serialization.Serializable


@Serializable
data class BarBellow(
    val alertId: String,
    val text: String,
    val bgColor: String,
    val textColor: String,
    val alertType: String,
    val button: Button,
    val closeButton: Boolean,
)

@Serializable
data class Button(
    val text: String,
    val bgColor: String,
    val textColor: String,
    val uri: String
)
