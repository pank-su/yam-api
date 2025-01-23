package su.pank.yamapi.builder

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import model.Language
import su.pank.yamapi.YaMusicApiClient

class YaMusicApiClientBuilder {
    var httpClient: HttpClient? = null

    private var _requestSettings: (HttpRequestBuilder.() -> Unit) = {}

    fun requestSettings(settings: (HttpRequestBuilder.() -> Unit)) {
        _requestSettings = settings
    }

    var baseUrl: String = "https://api.music.yandex.net"
    var token: String? = null
    var language: Language = Language.ru

    fun build(): YaMusicApiClient {
        val httpClient = httpClient.configure()

        return YaMusicApiClient(NetworkSettings(httpClient, baseUrl, _requestSettings), language, token)
    }

}

data class NetworkSettings(
    val httpClient: HttpClient,
    val baseUrl: String,
    val requestSettings: (HttpRequestBuilder.() -> Unit)
)


internal fun HttpClientConfig<*>.defaultConfig() {

    val json = Json {
        ignoreUnknownKeys = true
    }
    install(ContentNegotiation) {
        json(json)
    }
    install(HttpRequestRetry) {
        retryOnServerErrors(maxRetries = 5)
        exponentialDelay()
    }
}

private fun HttpClient?.configure(): HttpClient {
    return this?.config {
        defaultConfig()
    } ?: HttpClient {
        defaultConfig()
    }
}
