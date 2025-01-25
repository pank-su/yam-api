package su.pank.yamapi.landing.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.serializer
import model.playlist.Playlist
import su.pank.yamapi.model.album.Album


object BlockSerializer : KSerializer<BlockEntity> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("BlockEntity") {
            element("id", serialDescriptor<String>())
            element("type", serialDescriptor<BlockType>())
            element("data", buildClassSerialDescriptor("Any"))
        }

    @Suppress("UNCHECKED_CAST") // я знаю что тут всё норм
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