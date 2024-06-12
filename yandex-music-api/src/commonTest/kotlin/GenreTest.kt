
import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class GenreTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        println(client {
            this.token = token!!
        }.genres())
    }
}