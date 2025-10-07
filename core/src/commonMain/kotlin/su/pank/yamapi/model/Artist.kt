package su.pank.yamapi.model

import kotlinx.serialization.Serializable
import su.pank.yamapi.model.cover.Cover

/**
 * Артист.
 *
 * @param id Идентификатор.
 * @param name Имя.
 * @param cover Обложка.
 * @param various Различные.
 * @param composer Композитор.
 * @param genres Жанры.
 */
@Serializable
data class Artist(
    val id: Int,
    val name: String,
    val cover: Cover? = null,
    val various: Boolean? = null,
    val composer: Boolean? = null,
    val genres: List<String>? = null,
)
