import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class ExperimentsTest {
    @Test
    fun parsingDataIsValid() = runTest {
        client { }.account.experiments().forEach {
            println("${it.key}: ${it.value}")
        }
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client {
            this.token = token!!
        }.account.experiments()
    }
}