
@file:Suppress("JUnitTest")

package su.pank.yamapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import su.pank.yamapi.model.Language

val testJson =
    Json {
        ignoreUnknownKeys = true
    }

fun createMockedYamApiClient(handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData): YamApiClient {
    val mockEngine =
        MockEngine { request ->

            handler(this, request)
        }

    val client =
        HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(testJson)
            }
        }

    return YamApiClient(client, Language.ru)
}

fun mockJsonResponse(content: String): suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData =
    {
        respond(
            content = content,
            headers = headersOf(HttpHeaders.ContentType, "application/json"),
        )
    }

fun wrapWithBasicResponse(resultJson: String): String =
    """
    {
        "invocationInfo": {
            "hostname": "testhost",
            "req-id": "test-req-id",
            "exec-duration-millis": 100
        },
        "result": $resultJson
    }
    """.trimIndent()
