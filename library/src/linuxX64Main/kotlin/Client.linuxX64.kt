import io.ktor.client.engine.*
import io.ktor.client.engine.curl.*

actual fun getHttpClientEngine(): HttpClientEngine {
    return Curl.create()
}
