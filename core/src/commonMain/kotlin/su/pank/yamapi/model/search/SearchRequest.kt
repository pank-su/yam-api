package su.pank.yamapi.model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Запрос для поиска
 *
 * @property query запрос для поиска
 * @property isCorrect нужна ли корректировка запроса при орфографических ошибках
 * @property type тип запроса из которого будет зависеть вид ответа
 * @property page пэйгинация ответа, какая страница из полученного ответа необходима
 * @property playlistInBest TODO
 */
@Serializable
data class SearchRequest(
    @SerialName("text") val query: String,
    @SerialName("nocorrect") val isCorrect: Boolean,
    val type: QueryType, // TODO: сделать выбор что искать с помощью типов, что может помочь сохранить типизацию
    val page: Int = 0,
    @SerialName("playlist-in-best") val playlistInBest: Boolean?
)


class SearchRequestBuilder {
    var isCorrect: Boolean = false
    var type: QueryType = QueryType.All
    var page: Int = 0
    var playlistInBest: Boolean? = null

    fun build(query: String) = SearchRequest(query, isCorrect, type, page, playlistInBest)

}