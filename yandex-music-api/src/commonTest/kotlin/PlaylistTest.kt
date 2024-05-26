import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class PlaylistTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        val client = client {
            this.token = token!!
        }
        client.feed().generatedPlaylists.forEach {
            val ownerId = it.data.kind
            val playlistId = it.data.uid
            println(client.userPlaylist(ownerId, playlistId))
        }
        //client.playlistList()
    }

    @Test
    fun getLikes() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        val client = client {
            this.token = token!!
        }
        client.userPlaylist(3, client.userInfo().id.toInt())
    }
}