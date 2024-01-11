package model

import kotlinx.serialization.Serializable
import model.supplement.VideoProvider

@Serializable
data class Video(
    val title: String,
    val cover: String? = null,
    val embedUrl: String? = null,
    val provider: VideoProvider,
    val providerVideoId: String? = null,
    val youtubeUrl: String? = null,
    val thumbnailUrl: String? = null,
    val duration: Int? = null,
    val text: String? = null,
    val htmlAutoPlayVideoPlayer: String? = null,
    val regions: List<String>? = null,
)
