package model.rotor

import kotlinx.serialization.Serializable
import model.album.GenreType
import utils.IntOrStringSerializer

@Serializable
data class AdParams(
    @Serializable(with = IntOrStringSerializer::class) val partnerId: String,
    @Serializable(with = IntOrStringSerializer::class) val categoryId: String,
    val pageRef: String,
    val targetRef: String,
    val otherParams: String,
    val adVolume: Int,
    @Serializable(with = IntOrStringSerializer::class) val genreId: String? = null,
    val genreName: GenreType? = null
)