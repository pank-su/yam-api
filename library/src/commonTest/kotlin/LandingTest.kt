import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class LandingTest {

    @Test
    fun gettingTest() = runTest {
        val token = getenv("token") ?: return@runTest
        println(
            client { this.token = token }.tags("belarus")
        )
    }
}