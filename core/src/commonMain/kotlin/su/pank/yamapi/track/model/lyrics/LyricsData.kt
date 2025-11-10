package su.pank.yamapi.track.model.lyrics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Данные текста песни.
 *
 * @param downloadUrl URL для скачивания текста.
 * @param lyricId Идентификатор текста.
 * @param externalLyricId Внешний идентификатор текста.
 * @param writers Список авторов текста.
 * @param major Сервис откуда получен текст.
 */
@Serializable
data class LyricsData(
    val downloadUrl: String,
    val lyricId: Int,
    val externalLyricId: String,
    val writers: List<String>,
    val major: LyricsMajor
)

/**
 * Основная информация о сервисе текста песни.
 *
 * @param id Идентификатор сервиса.
 * @param name Название сервиса.
 * @param prettyName Читаемое название сервиса.
 */
@Serializable
data class LyricsMajor(
    val id: Int,
    val name: String,
    val prettyName: String? = null
)

/**
 * Формат текста песни.
 */
@Serializable
enum class LyricsFormat {
    LRC,

    TEXT
}

@Serializable
internal data class LyricsRequest(
    val format: LyricsFormat,
    val timeStamp: Long,
    val sign: String,
)