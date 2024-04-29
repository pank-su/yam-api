import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import model.landing.BlockType
import kotlin.test.Test

class LandingTest {

    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        client { this.token = token!! }.landing(BlockType.PersonalPlaylists)
    }
}