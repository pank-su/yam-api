import dsl.client
import exceptions.NotAuthenticatedException
import io.getenv
import kotlinx.coroutines.test.runTest
import model.account.Theme
import model.account.UserSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

class SettingsTest {
    @Test
    fun gettingSettings() = runTest {
        assertFailsWith<NotAuthenticatedException> {
            client { }.getSettings()
        }
        val token = getenv("token") ?: return@runTest
        if (token == "") return@runTest
        client { this.token = token }.getSettings()
    }

    @Test
    fun updateSettings() = runTest {
        val token = getenv("token") ?: return@runTest
        if (token == "") return@runTest
        val client = client { this.token = token }
        val settings = client.getSettings()
        assertEquals(settings.update(UserSettings::volumePercents, 80).volumePercents, 80)
        assertEquals(settings.update(UserSettings::volumePercents, 75).volumePercents, 75)
        assertEquals(settings.update(UserSettings::theme, Theme.White).theme, Theme.White)
        assertEquals(settings.update(UserSettings::theme, Theme.Black).theme, Theme.Black)
        assertNotEquals(settings, settings.update(UserSettings::autoPlayRadio, !settings.autoPlayRadio))
    }
}