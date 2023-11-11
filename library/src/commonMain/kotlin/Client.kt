import dsl.YandexMusicTagMaker
import io.ktor.client.*
import io.ktor.client.plugins.logging.*


@YandexMusicTagMaker
class Client {
    var baseUrl: String = "https://api.music.yandex.net"
    val token: String? = null
    var language = "ru"

    var httpClient = HttpClient {
        install(Logging)
    }
}