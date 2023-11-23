
import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AdsTest {

    @Test
    fun gettingAds() = runTest {
        val ad = client { }.ads()

        val token = getenv("token") ?: return@runTest
        if (token == "") return@runTest
        client { this.token = token }.ads()
    }
}