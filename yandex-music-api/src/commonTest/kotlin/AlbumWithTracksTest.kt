import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AlbumWithTracksTest {
    @Test
    fun gettingTest() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        println(client {
            this.token = token!!
        }.albumsWithTracks(4527991))

    }
}