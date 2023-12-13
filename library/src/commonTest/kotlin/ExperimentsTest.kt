import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ExperimentsTest {
    @Test
    fun gettingTest() = runTest{
        client { }.accountExperiments().value?.forEach {
            println("${it.key}: ${it.value}")
        }
        val token = getenv("token") ?: return@runTest
        if (token == "") return@runTest
        client {
            this.token = token
        }.accountExperiments()
    }
}