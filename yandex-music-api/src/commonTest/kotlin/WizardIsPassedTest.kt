import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFails

class WizardIsPassedTest {

    @Test
    fun parsingDataIsValid() = runTest {

        assertFails {
            client {

            }.landing.feedWizardIsPassed()
        }
        val token = getenv("token") ?: return@runTest
        if (token.isBlank()) return@runTest

        client {
            this.token = token
        }.landing.feedWizardIsPassed()
    }
}