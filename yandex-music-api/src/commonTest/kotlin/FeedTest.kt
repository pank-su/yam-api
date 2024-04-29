import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import model.cover.CoverSize
import kotlin.test.Test

class FeedTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        val client = client {
            this.token = token!!
        }
        println(client.feed())

        client.feed().generatedPlaylists.forEach {
            println(it.data.getUrlBackgroundImage(CoverSize.`400x400`))
        }
    }
}