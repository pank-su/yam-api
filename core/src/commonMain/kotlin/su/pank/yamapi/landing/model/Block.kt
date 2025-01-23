package su.pank.yamapi.landing.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import model.album.Album


@Serializable
data class Block(
    val id: String,
    val type: BlockType,
    val typeForFrom: BlockType,
    val title: String,
    val entities: List<BlockEntity>
)


@Serializable(with = BlockSerializer::class)
data class BlockEntity(
    val id: String,
    val type: BlockType,
    val data: Any
)

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

@Serializable
data class MixLink(
    val title: String,
    val url: String,
    val urlScheme: String,
    val textColor: String,
    val backgroundColor: String,
    val backgroundImageUri: String,
    val coverWhite: String? = null,
    val coverUri: String? = null,
)


@Serializable
data class Promotion(val title: String)


@Serializable
data class Podcast(val podcast: Album)
