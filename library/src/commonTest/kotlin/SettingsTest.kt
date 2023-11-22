import dsl.client
import exceptions.NotAuthenticatedException
import io.getenv
import kotlinx.coroutines.test.runTest
import model.account.Theme
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SettingsTest {
    @Test
    fun gettingSettings() = runTest {
        assertFailsWith<NotAuthenticatedException> {
            client { }.getSettings()
        }
        val token = getenv("token") ?: return@runTest
        ///if (token == "") return@runTest
        client { this.token = token }.getSettings()
    }

    @Test
    fun updateSettings() = runTest {
        val token = getenv("token") ?: return@runTest
        if (token == "") return@runTest
        val client = client { this.token = token }
        val settings = client.getSettings()
        val newSettings = client.setSettings(settings.copy(theme = Theme.Light, modified = null, uid = null))
        println(newSettings)
    }
}