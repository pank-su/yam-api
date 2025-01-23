package landing.model

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
import model.album.Album
import model.artist.Artist
import model.playlist.Playlist
import su.pank.yamapi.landing.model.Context
import su.pank.yamapi.landing.model.PlayContextsData

object PlayContextsDataSerializer : KSerializer<PlayContextsData> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("BlockEntity") {
            element("client", serialDescriptor<String>())
            element("context", serialDescriptor<Context>())
            element("contextItem", serialDescriptor<String>())
            element("payload", buildClassSerialDescriptor("Any"))
        }

    @Suppress("UNCHECKED_CAST")
    private fun getDataSerializer(type: Context): KSerializer<Any> = when (type) {
        Context.Playlist -> serializer<Playlist>()
        Context.Album -> serializer<Album>()
        Context.Artist -> serializer<Artist>()
    } as KSerializer<Any>

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): PlayContextsData = decoder.decodeStructure(descriptor) {
        if (decodeSequentially()) {
            val client = decodeStringElement(descriptor, 0)
            val context = decodeSerializableElement(descriptor, 1, serializer<Context>())
            val contextItem = decodeStringElement(descriptor, 0)
            val payload = decodeSerializableElement(descriptor, 2, getDataSerializer(context))
            PlayContextsData(client, context, contextItem, payload)
        } else {
            require(decodeElementIndex(descriptor) == 0) { "dataType field should precede payload field" }
            val client = decodeStringElement(descriptor, 0)
            val context = when (val index = decodeElementIndex(descriptor)) {
                1 -> decodeSerializableElement(descriptor, 1, serializer<Context>())
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            val contextItem = when (val index = decodeElementIndex(descriptor)) {
                2 -> decodeStringElement(descriptor, 2)
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            val payload = when (val index = decodeElementIndex(descriptor)) {
                3 -> decodeSerializableElement(descriptor, 3, getDataSerializer(context))
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            PlayContextsData(client, context, contextItem, payload)
        }
    }

    override fun serialize(encoder: Encoder, value: PlayContextsData) {
        TODO("Not yet implemented")
    }
}