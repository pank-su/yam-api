package su.pank.yamapi.model.search

import kotlinx.serialization.Serializable
import su.pank.yamapi.model.search.Best

/**
 * Подсказки для ответа
 */
@Serializable
data class Suggestions(val best: Best? = null, val suggestions: List<String>)
