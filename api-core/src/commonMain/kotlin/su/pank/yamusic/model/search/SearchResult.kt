package model.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult<T>(val total: Int, val perPage: Int, val order: Int, val results: List<T>)
