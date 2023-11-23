import dsl.YandexMusicTagMaker
import exceptions.SessionExpiredException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import model.BasicResponse
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

    @OptIn(InternalSerializationApi::class)
    private suspend inline fun <reified T : Result> request(
        components: List<String>,
        method: HttpMethod = HttpMethod.Get,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.request(baseUrl) {
            requestSettings()
            this.method = method
            url {
                appendPathSegments(components)
            }
            headers {
                append("X-Yandex-Music-Client", "YandexMusicAndroid/24023231")
                append("USER_AGENT", "Yandex-Music-API")
                if (token != "") {
                    append(HttpHeaders.Authorization, "OAuth $token")
                }
            }
            if (method == HttpMethod.Post) {
                headers {
                    append(HttpHeaders.ContentType, "form-encoded")
                }
                formData {
                    parameters {
                        body.forEach {
                            append(it.key, it.value)
                        }
                    }
                }
            }
            headers {
                remove(HttpHeaders.ContentType)
                remove(HttpHeaders.ContentLength)
            }
        }.body<BasicResponse<T>>().result.apply { client = this@Client }
    }

    internal suspend inline fun <reified T : Result> requestForm(
        vararg components: String,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return httpClient.submitForm(baseUrl, formParameters =
        parameters {
            body.forEach {
                append(it.key, it.value)
            }
        }) {
            requestSettings()
            url {
                appendPathSegments(components.toList())
            }
            headers {
                append("X-Yandex-Music-Client", "YandexMusicAndroid/24023231")
                append("USER_AGENT", "Yandex-Music-API")
                if (token != "") {
                    append(HttpHeaders.Authorization, "OAuth $token")
                }
            }
        }.body<BasicResponse<T>>().result.apply { client = this@Client }
    }


    private suspend inline fun <reified T : Result> request(
        vararg components: String,
        method: HttpMethod = HttpMethod.Get
    ): T {
        return request(components.toList(), method)
    }

    internal suspend inline fun <reified T : Result> requestPost(
        vararg components: String,
        body: HashMap<String, String> = hashMapOf()
    ): T {
        return request(components.toList(), method = HttpMethod.Post, body)
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
//    suspend fun setSettings(settings: UserSettings) =
//       requestPost<UserSettings>("account", "settings",  body = settings)

}
