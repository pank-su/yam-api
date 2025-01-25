package su.pank.yamapi.model.search


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
import model.Video
import model.album.Album
import model.artist.Artist
import model.playlist.Playlist
import su.pank.yamapi.account.model.User
import su.pank.yamapi.landing.model.Podcast
import su.pank.yamapi.track.model.TrackData

/**
 * Десериализация лучшего подходящего ответа в зависимости от полученного типа
 */
object BestSerializer : KSerializer<Best> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("BlockEntity") {
            element("type", serialDescriptor<QueryResponseType>())
            element("result", buildClassSerialDescriptor("Any"))
        }

    private fun getResultSerializer(type: QueryResponseType): KSerializer<Any> = when (type) {
        QueryResponseType.Artist -> serializer<Artist>()
        QueryResponseType.User -> serializer<User>()
        QueryResponseType.Album -> serializer<Album>()
        QueryResponseType.Playlist -> serializer<Playlist>()
        QueryResponseType.Track -> serializer<TrackData>()
        QueryResponseType.Podcast -> serializer<Podcast>()
        QueryResponseType.Video -> serializer<Video>()
        QueryResponseType.PodcastEpisode -> serializer<TrackData>()
    } as KSerializer<Any>

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Best = decoder.decodeStructure(descriptor) {
        if (decodeSequentially()) {
            val type = decodeSerializableElement(descriptor, 0, serializer<QueryResponseType>())
            val data = decodeSerializableElement(descriptor, 1, getResultSerializer(type))
            Best(type, data)
        } else {
            val type = when (val index = decodeElementIndex(descriptor)) {
                0 -> decodeSerializableElement(descriptor, 0, serializer<QueryResponseType>())
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            val data = when (val index = decodeElementIndex(descriptor)) {
                1 -> decodeSerializableElement(descriptor, 1, getResultSerializer(type))
                CompositeDecoder.DECODE_DONE -> throw SerializationException("data is missing")
                else -> error("Unexpected index: $index")
            }
            Best(type, data)
        }
    }

    override fun serialize(encoder: Encoder, value: Best) {
        TODO("Не требуется")
    }
}