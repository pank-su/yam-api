package landing.model

import kotlinx.serialization.Serializable

@Serializable
data class MixLink(
    val title: String,
    val url: String,
    val urlScheme: String,
    val textColor: String,
    val backgroundColor: String,
    val backgroundImageUri: String,
    val coverWhite: String? = null,
    val coverUri: String? = null,
)
