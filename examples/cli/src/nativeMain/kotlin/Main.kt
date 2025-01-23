import kotlinx.coroutines.runBlocking
import su.pank.yamusic.createYaMusicApiClient

fun main() {
    val client = createYaMusicApiClient { }
    runBlocking {
        println(client.account.status())
    }
}