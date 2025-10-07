package su.pank.yamapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Icon(
    val backgroundColor: String,
    val imageUrl: String,
)
