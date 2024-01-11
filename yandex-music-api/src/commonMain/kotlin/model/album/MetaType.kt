package model.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MetaType {
    @SerialName("music")
    Music,

    @SerialName("podcast")
    Podcast,

    @SerialName("podcast-episode")
    PodcastEpisode,

    @SerialName("audiobook")
    AudioBook
}