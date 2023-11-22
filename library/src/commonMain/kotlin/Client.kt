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
import model.EmptyRequest
import model.Result
import model.account.Status
import model.account.UserSettings

expect fun getHttpClientEngine(): HttpClientEngine

@YandexMusicTagMaker
class Client {
    var baseUrl: String = "https://api.music.yandex.net"
    var token: String = ""
    var language = "ru"
    var me: Status? = null


    var httpClientEngine: HttpClientEngine = getHttpClientEngine()

    var loggingSettings: Logging.Config.() -> Unit = {
        logger = Logger.DEFAULT
        this.level = LogLevel.BODY
    }

    var requestSettings: HttpRequestBuilder.() -> Unit = {

    }


    private var httpClient = HttpClient(httpClientEngine) {
        install(Logging) {
            loggingSettings()
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private suspend inline fun <reified T : Result> request(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get,
        body: Result = EmptyRequest()
    ): T {
        return httpClient.request(baseUrl) {
            requestSettings()
            this.method = method
            url {
                appendPathSegments(components.toList())
            }
            if (token != "") {
                headers {
                    append(HttpHeaders.Authorization, "OAuth $token")
                }
            }
            if (method == HttpMethod.Post) {
                contentType(ContentType.Application.Json)
                setBody(body.apply { this.invocationInfo = null } as T)
            }
        }.body<BasicResponse<T>>().result
    }

    suspend fun init(init: Client.() -> Unit) {
        this.init()
        try {
            me = getStatus()
        } catch (e: SessionExpiredException) {
            println("Токен некореектный или истёк")
        }
    }

    suspend fun getStatus() = request<Status>("account", "status")
    suspend fun getSettings() = request<UserSettings>("account", "settings")
    suspend fun setSettings(settings: UserSettings) =
        request<UserSettings>("account", "settings", method = HttpMethod.Post, body = settings)

}
