import dsl.YandexMusicTagMaker
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import model.BasicResponse
import model.account.Status

expect fun getHttpClientEngine(): HttpClientEngine

@YandexMusicTagMaker
class Client {
    var baseUrl: String = "https://api.music.yandex.net"
    var token: String? = null
    var language = "ru"
    lateinit var me: Status


    var httpClientEngine: HttpClientEngine = getHttpClientEngine()

    private var httpClient = HttpClient(httpClientEngine) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true

            })

        }
    }

    suspend fun init(init: Client.() -> Unit) {
        this.init()
        if (token == null) return
        try {
            me = getStatus()!!
        } catch (e: NullPointerException) {
            println("Токен некореектный")
        }

    }

    suspend fun getStatus(): Status? {
        return httpClient.request(baseUrl) {
            url {
                appendPathSegments("account", "status")
            }
            headers {
                append(HttpHeaders.Authorization, "OAuth $token")
            }
        }.body<BasicResponse<Status>>().result
    }

}
