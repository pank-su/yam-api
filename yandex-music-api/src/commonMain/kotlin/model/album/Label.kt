package model.album

import kotlinx.serialization.Serializable

@Serializable(with = LabelSerializer::class)
data class Label(val id: Int, val name: String)

