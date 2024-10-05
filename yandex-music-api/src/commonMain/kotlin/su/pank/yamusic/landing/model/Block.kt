package su.pank.yamusic.landing.model.block

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.json.JsonNames
import landing.model.MixLink
import landing.model.PlayContextsData
import landing.model.Podcast
import landing.model.Promotion
import su.pank.yamusic.landing.model.chart.ChartItem
import landing.model.feed.GeneratedPlaylist
import model.album.Album
import model.playlist.Playlist

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

object BlockSerializer : KSerializer<BlockEntity> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("BlockEntity") {
            element("id", serialDescriptor<String>())
            element("type", serialDescriptor<BlockType>())
            element("data", buildClassSerialDescriptor("Any"))
        }

    private fun getDataSerializer(type: BlockType): KSerializer<Any> = when (type) {
        BlockType.PersonalPlaylists -> serializer<GeneratedPlaylist>()
        BlockType.Promotions -> serializer<Promotion>()
        BlockType.NewReleases -> serializer<Album>()
        BlockType.NewPlaylists -> serializer<Playlist>()
        BlockType.Mixes -> serializer<MixLink>()
        BlockType.Chart -> serializer<ChartItem>()
        BlockType.Artists -> serializer<GeneratedPlaylist>()
        BlockType.Podcasts -> serializer<Podcast>()
        BlockType.Albums -> serializer<Album>()
        BlockType.Playlists -> serializer<GeneratedPlaylist>()
        BlockType.PlayContexts -> serializer<PlayContextsData>()
    } as KSerializer<Any>

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): BlockEntity = decoder.decodeStructure(descriptor) {
        if (decodeSequentially()) {
            val id = decodeStringElement(descriptor, 0)
            val type = decodeSerializableElement(descriptor, 1, serializer<BlockType>())
            val data = decodeSerializableElement(descriptor, 2, getDataSerializer(type))
            BlockEntity(id, type, data)
        } else {
            require(decodeElementIndex(descriptor) == 0) { "dataType field should precede payload field" }
            val id = decodeStringElement(descriptor, 0)
            val type = when (val index = decodeElementIndex(descriptor)) {
                1 -> decodeSerializableElement(descriptor, 1, serializer<BlockType>())
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            val data = when (val index = decodeElementIndex(descriptor)) {
                2 -> decodeSerializableElement(descriptor, 2, getDataSerializer(type))
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            BlockEntity(id, type, data)
        }
    }

    override fun serialize(encoder: Encoder, value: BlockEntity) {
        TODO("Not yet implemented")
    }
}