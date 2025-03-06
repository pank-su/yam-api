package su.pank.yamapi.utils

import io.ktor.http.*
import kotlinx.serialization.json.*

// 1. Функция-расширение для преобразования сериализуемого объекта в Parameters:
inline fun <reified T> ParametersBuilder.setBody(body: T) {
    val json: Json = Json { encodeDefaults = true }

    // Сериализуем объект в JsonElement
    val element = json.encodeToJsonElement(this)
    require(element is JsonObject) {
        "Ожидался JsonObject, но получил ${element::class.simpleName}"
    }

    element.forEach { (key, value) ->
        when (value) {
            // Примитив (числа, строки, булевы и т.п.)
            is JsonPrimitive -> append(key, value.content)
            // Массив (если хотите обработать массивы, можно решить, как именно их превращать в строку)
            is JsonArray -> appendAll(key, value.map { it.toString() })
            // Объекты (если в модели есть вложенные объекты, нужно решить, как их сериализовать)
            is JsonObject -> append(key, value.toString())
            else -> append(key, value.toString())
        }
    }

}
