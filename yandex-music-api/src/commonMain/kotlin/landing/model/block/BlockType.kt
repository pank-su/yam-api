package landing.model.block

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
    @JsonNames("promotion", "promotions")
    Promotions,

    @SerialName("new-releases")
    NewReleases,

    @SerialName("new-playlists")
    @JsonNames("playlist", "new-playlists")
    NewPlaylists,

    @SerialName("mixes")
    @JsonNames("mix-link", "mixes")
    Mixes,

    @SerialName("chart")
    @JsonNames("chart-item", "chart")
    Chart,

    @SerialName("artists")
    Artists,


    @SerialName("albums")
    @JsonNames("album", "albums")
    Albums,

    @SerialName("playlists")
    Playlists,

    @SerialName("play_contexts")
    @JsonNames("play-context", "play-contexts", "play_contexts")
    PlayContexts,

    @SerialName("podcasts")
    @JsonNames("non-music_main_podcasts", "podcasts", "podcast")
    Podcasts,


}