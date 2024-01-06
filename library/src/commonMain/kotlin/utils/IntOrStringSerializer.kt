package utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

object IntOrStringSerializer : KSerializer<Int> {
    override val descriptor: SerialDescriptor
        get() = serialDescriptor<Int>()

    override fun deserialize(decoder: Decoder): Int {
        val jsonDecoder = decoder as? JsonDecoder ?: throw Exception("Only JSON format is expected")
        val json = jsonDecoder.decodeJsonElement()
        return if (json is JsonPrimitive && json.isString) json.content.toInt()
        else return json.jsonPrimitive.int
    }

    override fun serialize(encoder: Encoder, value: Int) {
        TODO("Не требуется")
    }

}