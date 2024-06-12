import dsl.client
import exceptions.ValidateException
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class PermissionAlertsTest {
    @Test
    fun parsingDataIsValid() = runTest {
        assertFailsWith<ValidateException> {
            client { }.account.permissionAlerts()
        }
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        val client = client { this.token = token!! }.account.permissionAlerts()
    }
}