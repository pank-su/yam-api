import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class GenreTest {
    @Test
    fun gettingTest() = runTest{
        val token = getenv("token") ?: return@runTest
        if (token.isBlank()) return@runTest
        client {  }.genres()
    }
}