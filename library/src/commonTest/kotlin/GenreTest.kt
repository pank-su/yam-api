import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class GenreTest {
    @Test
    fun gettingTest() = runTest{
        getenv("token") ?: return@runTest
        client {  }.genres()
    }
}