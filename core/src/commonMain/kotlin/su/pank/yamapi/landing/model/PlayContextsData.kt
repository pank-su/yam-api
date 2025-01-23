package su.pank.yamapi.landing.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import landing.model.PlayContextsDataSerializer


@Serializable(with = PlayContextsDataSerializer::class)
data class PlayContextsData(val client: String, val context: Context, val contextItem: String, val payload: Any)


@Serializable
enum class Context {

    @SerialName("playlist")
    Playlist,

    @SerialName("album")
    Album,

    @SerialName("artist")
    Artist
}
