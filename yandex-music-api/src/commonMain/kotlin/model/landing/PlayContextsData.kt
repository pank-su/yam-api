package model.landing

import kotlinx.serialization.Serializable

@Serializable(with = PlayContextsDataSerializer::class)
data class PlayContextsData(val client: String, val context: Context, val contextItem: String, val payload: Any)


