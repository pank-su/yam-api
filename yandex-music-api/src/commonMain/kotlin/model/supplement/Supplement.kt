package model.supplement

import kotlinx.serialization.Serializable
import model.Result

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
) : Result()