package su.pank.yamapi.model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QueryResponseType {
    @SerialName("artist")
    Artist,

    @SerialName("user")
    User,

    @SerialName("album")
    Album,

    @SerialName("playlist")
    Playlist,

    @SerialName("track")
    Track,

    @SerialName("podcast")
    Podcast,

    @SerialName("video")
    Video,

    @SerialName("podcast_episode")
    PodcastEpisode
}