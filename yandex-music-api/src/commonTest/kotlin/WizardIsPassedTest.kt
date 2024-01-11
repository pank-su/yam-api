import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFails

class WizardIsPassedTest {

    @Test
    fun gettingTest() = runTest {

        assertFails {
            client {

            }.feedWizardIsPassed()
        }
        val token = getenv("token") ?: return@runTest
        if (token.isBlank()) return@runTest

        client {
            this.token = token
        }.feedWizardIsPassed()
    }
}