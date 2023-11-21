import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.java.*

actual fun getHttpClientEngine(): HttpClientEngine {
    return HttpClient(Java).engine
}