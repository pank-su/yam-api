package model.artist

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

object ArtistNameSerializer : JsonTransformingSerializer<String>(String.serializer()) {
    override fun transformSerialize(element: JsonElement): JsonElement {
        return Json.decodeFromString<JsonObject>(
            element.toString().replace("/", "")
                .replace("\\", "")
                .removePrefix("\"").removeSuffix("\"")
        )
    }

}