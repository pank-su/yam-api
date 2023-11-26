package model.playlist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CoverType {
    @SerialName("pic")
    Picture,

    @SerialName("from-artist-photos")
    FromArtistPhotos,

    @SerialName("from-album-cover")
    FromAlbumCover
}

enum class CoverSize {
    `50x50`, `100x100`, `200x200`, `400x400`, `600x600`, `800x800`, `1000x1000`
}

@Serializable
data class Cover(
    val type: CoverType,
    val uri: String,
    val prefix: String? = null,
    val dir: String? = null,
    val version: String? = null,
    val custom: Boolean? = null
) {


    fun getUrl(size: CoverSize) = "https://${uri.replace("%%", size.toString())}"
}
