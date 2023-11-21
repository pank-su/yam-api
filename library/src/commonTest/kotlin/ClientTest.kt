import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class ClientTest {
    @Test
    fun simpleTest() = runTest {
        val token = getenv("token")
        val client = client {
            this.token = token
        }
        println(client.me)
    }
}