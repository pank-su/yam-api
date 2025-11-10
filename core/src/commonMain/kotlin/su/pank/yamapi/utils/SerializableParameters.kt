package su.pank.yamapi.utils

import io.ktor.http.*
import kotlinx.serialization.json.*

/**
 * Преобразует сериализуемый объект в параметры.
 *
 * @param T Тип объекта.
 * @param body Объект.
 */
inline fun <reified T> ParametersBuilder.setBody(body: T) {
    val json: Json = Json { encodeDefaults = true }

    // Сериализуем объект в JsonElement
    val element = json.encodeToJsonElement(body)
    require(element is JsonObject) {
        "Ожидался JsonObject, но получил ${element::class.simpleName}"
    }

    element.forEach { (key, value) ->
        when (value) {
            is JsonPrimitive -> append(key, value.content)
            is JsonArray -> appendAll(key, value.map { it.toString() })
            is JsonObject -> append(key, value.toString())
            else -> append(key, value.toString())
        }
    }
}
