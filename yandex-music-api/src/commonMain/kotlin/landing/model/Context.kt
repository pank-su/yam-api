package landing.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Context {

    @SerialName("playlist")
    Playlist,

    @SerialName("album")
    Album,
    @SerialName("artist")
    Artist
}