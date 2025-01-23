package model.playlist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CaseForms(
    @SerialName("accusative")
    val accusative: String,
    @SerialName("dative")
    val dative: String,
    @SerialName("genitive")
    val genitive: String,
    @SerialName("instrumental")
    val instrumental: String,
    @SerialName("nominative")
    val nominative: String,
    @SerialName("prepositional")
    val prepositional: String
)