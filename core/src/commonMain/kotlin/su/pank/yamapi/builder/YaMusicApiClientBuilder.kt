package su.pank.yamapi.builder

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.model.Language

/**
 * Билдер для создания клиента YamApiClient.
 */
class YaMusicApiClientBuilder {
    /**
     * HTTP клиент. Если null, будет создан новый.
     */
    var httpClient: HttpClient? = null

    /**
     * Базовый URL для API.
     */
    var baseUrl: String = "https://api.music.yandex.net"

    /**
     * Токен авторизации.
     */
    var token: String? = null

    /**
     * Язык запросов.
     */
    var language: Language = Language.ru

    /**
     * Уровень логирования.
     */
    var logLevel = LogLevel.NONE

    /**
     * User-Agent для запросов.
     */
    var userAgent = "yam-api"

    /**
     * Строит и возвращает экземпляр YamApiClient.
     *
     * @return YamApiClient
     */
    fun build(): YamApiClient {
        val httpClient = httpClient.configure(this)

        return YamApiClient(httpClient, language)
    }
}

private fun HttpClient?.configure(builder: YaMusicApiClientBuilder): HttpClient =
    (this ?: HttpClient {}).config {
        val json =
            Json {
                ignoreUnknownKeys = true
            }
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
        install(Logging) {
            level = builder.logLevel
        }

        defaultRequest {
            url(builder.baseUrl)

            headers {
                append("X-Yandex-Music-Client", "YandexMusicAndroid/24022571")
                append("USER_AGENT", builder.userAgent)
                append("Accept-Language", builder.language.toString())

                if (builder.token != null) {
                    append(HttpHeaders.Authorization, "OAuth ${builder.token}")
                }
            }
        }
    }
