package model.landing

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
enum class BlockType {
    @SerialName("personalplaylists")
    @JsonNames("personalplaylists", "personal-playlists", "personal-playlist")
    PersonalPlaylists,

    @SerialName("promotions")
    @JsonNames("promotions")
    Promotions,

    @SerialName("new-releases")
    NewReleases,

    @SerialName("new-playlists")
    NewPlaylists,

    @SerialName("mixes")
    Mixes,

    @SerialName("chart")
    Chart,

    @SerialName("artists")
    Artists,

    @SerialName("albums")
    Albums,

    @SerialName("playlists")
    Playlists,

    @SerialName("play_contexts")
    PlayContexts
}