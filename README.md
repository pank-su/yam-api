## Yandex Music API (Kotlin Multiplatform)

Реализация Yandex Music Api (основываясь на
репозитории [MarshalX](https://github.com/MarshalX/yandex-music-api/tree/main)) на Kotlin Multiplatform

Стадия проекта: **В разработке**

Стадия разработки документации: **Не начато**

Пример использования:

```kotlin
suspend fun main() {
    val client = client {
        token = "YOUR TOKEN"
    }

    println(client.me)
}
```

