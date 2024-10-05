package model.search

import kotlinx.serialization.Serializable

@Serializable(with = BestSerializer::class)
data class Best(val type: QueryResponseType, val result: Any)
