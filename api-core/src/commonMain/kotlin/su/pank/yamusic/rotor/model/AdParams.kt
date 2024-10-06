package su.pank.yamusic.rotor.model

import kotlinx.serialization.Serializable
import model.album.GenreType
import su.pank.yamusic.utils.IntOrStringSerializer

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