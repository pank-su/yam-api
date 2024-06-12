package track.model.supplement

import kotlinx.serialization.Serializable


@Serializable
data class Lyrics(
    val id: Int,
    val lyrics: String,
    val hasRights: Boolean,
    val fullLyrics: String,
    val showTranslation: Boolean,
    val textLanguage: String? = null,
    val url: String? = null
)
