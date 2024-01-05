import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class TrackSimilarTest {
    @Test
    fun gettingTest() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        println(client { }.tracksSimilar(109140203).similarTracks)
    }
}