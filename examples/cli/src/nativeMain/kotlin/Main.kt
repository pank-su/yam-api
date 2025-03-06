import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.runBlocking
import su.pank.yamapi.builder.createYaMusicApiClient

fun main() {
    val client = createYaMusicApiClient {
        logLevel = LogLevel.BODY
    }
    runBlocking {
        println(client.account.status())
    }
}