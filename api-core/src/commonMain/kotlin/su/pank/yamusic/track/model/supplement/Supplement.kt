package su.pank.yamusic.track.model.supplement

import kotlinx.serialization.Serializable

@Serializable
data class Supplement(
    val id: Int,
    @Deprecated(
        "Получение текста из дополнительной информации устарело.",
        replaceWith = ReplaceWith("Client().tracksLyrics()")
    )
    val lyrics: Lyrics? = null,
    val video: List<VideoSupplement> = listOf(),
    val clips: List<Clip>? = null,
    val radioIsAvailable: Boolean? = null,
    val description: String? = null
)

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
