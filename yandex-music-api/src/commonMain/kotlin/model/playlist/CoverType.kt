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