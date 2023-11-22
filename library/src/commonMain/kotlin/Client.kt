import dsl.YandexMusicTagMaker
import exceptions.SessionExpiredException
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
    var token: String = ""
    var language = "ru"
    var me: Status? = null


    var httpClientEngine: HttpClientEngine = getHttpClientEngine()

    val loggingSettings: (Logging.Config) -> Unit = {
        it.logger = Logger.DEFAULT
        it.level = LogLevel.BODY
    }

    private var httpClient = HttpClient(httpClientEngine) {
        install(Logging) {
            loggingSettings(this)
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })

        }
    }

    suspend fun init(init: Client.() -> Unit) {
        this.init()
        try {
            me = getStatus()
        } catch (e: SessionExpiredException) {
            println("Токен некореектный или истёк")
        }
    }

    suspend fun getStatus(): Status {
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
