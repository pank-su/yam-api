package model.search

import kotlinx.serialization.Serializable

@Serializable
data class Suggestions(val best: Best? = null, val suggestions: List<String>)
