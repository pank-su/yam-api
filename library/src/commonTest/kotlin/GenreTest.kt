import dsl.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class GenreTest {
    @Test
    fun gettingTest() = runTest{
        client {  }.genres()
    }
}