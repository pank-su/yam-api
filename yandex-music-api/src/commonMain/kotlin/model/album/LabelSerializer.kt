package model.album

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.*

object LabelSerializer : KSerializer<Label> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("Label") {
            element<Int>("id")
            element<String>("name")
        }

    override fun deserialize(decoder: Decoder): Label {
        val jsonDecoder = decoder as? JsonDecoder ?: throw Exception("Only JSON format is expected")
        val json = jsonDecoder.decodeJsonElement()
        return if (json is JsonPrimitive && json.isString)
            Label(0, json.content)
        else {
            Label(
                json.jsonObject["id"]?.jsonPrimitive?.int ?: 0,
                json.jsonObject["name"]?.jsonPrimitive?.content ?: ""
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Label) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.id)
            encodeStringElement(descriptor, 1, value.name)
        }
    }
}