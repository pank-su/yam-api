import dsl.client
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ClientTest {
    @Test
    fun simpleTest() = runTest {
        val client = client {

        }

        val response = client.httpClient.get("https://pank.su")
        println(response.bodyAsText())
    }
}