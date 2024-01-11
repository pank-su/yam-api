package model.search

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class Suggestions(val best: Best? = null, val suggestions: List<String>) : Result()
