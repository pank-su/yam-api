import dsl.YandexMusicTagMaker
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import model.account.Status

expect fun getHttpClientEngine(): HttpClientEngine

@YandexMusicTagMaker
class Client {
    var baseUrl: String = "https://api.music.yandex.net"
    val token: String? = null
    var language = "ru"
    lateinit var me: Status


    var httpClientEngine: HttpClientEngine = getHttpClientEngine()

    private var httpClient = HttpClient(httpClientEngine) {
        install(Logging)
        install(ContentNegotiation) {
            json()
        }
    }

    fun init(init: Client.() -> Unit) {
        this.init()
        //me =
    }

    fun getStatus() {

    }

}
