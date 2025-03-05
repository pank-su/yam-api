package su.pank.yamapi.builder

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.model.Language

class YaMusicApiClientBuilder {
    var httpClient: HttpClient? = null

    var baseUrl: String = "https://api.music.yandex.net"
    var token: String? = null
    var language: Language = Language.ru

    fun build(): YamApiClient {
        val httpClient = httpClient.configure(baseUrl)

        return YamApiClient(httpClient, language, token)
    }

}



internal fun HttpClientConfig<*>.defaultConfig(baseUrl: String) {

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
    defaultRequest {
        url(baseUrl)
    }
}

private fun HttpClient?.configure(baseUrl: String): HttpClient {
    return (this ?: HttpClient{}).config {
        defaultConfig(baseUrl)

    }
}
