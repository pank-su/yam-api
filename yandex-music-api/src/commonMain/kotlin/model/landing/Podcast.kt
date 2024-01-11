package model.landing

import kotlinx.serialization.Serializable
import model.album.Album

@Serializable
data class Podcast(val podcast: Album)
