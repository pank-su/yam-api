import kotlinx.coroutines.runBlocking
import su.pank.yamapi.builder.createYaMusicApiClient

fun main() {
    val client = createYaMusicApiClient { }
    runBlocking {
        println(client.account.status())
    }
}