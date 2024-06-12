import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AdsTest {

    @Test
    fun gettingAds() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val ad = client { }.account.ads()

        client {
            this.token = token!!
        }.account.ads()
    }
}