import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import model.playlist.CoverSize
import kotlin.test.Test

class FeedTest {
    @Test
    fun gettingTest() = runTest {
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