import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import okhttp3.OkHttpClient

actual fun getHttpClientEngine(): HttpClientEngine {
    return OkHttpEngine(OkHttpConfig())
}