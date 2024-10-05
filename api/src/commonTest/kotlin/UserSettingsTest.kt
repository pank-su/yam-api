import dsl.client
import su.pank.yamusic.exceptions.NotAuthenticatedException
import io.getenv
import kotlinx.coroutines.test.runTest
usic.account.model.UserSimport kotlin.t.assertNotEquals

class UserSettingsTest {
    @Test
    fun gettingUserSettings() = runTest {
        assertFailsWith<NotAuthenticatedException> {
            client { }.account.settings()
        }
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.account.settings()
    }

    @Test
    fun updateSettings() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client { this.token = token!! }
        val settings = client.account.settings()
//        assertEquals(settings.update(UserSettings::volumePercents, 80).volumePercents, 80)
//        assertEquals(settings.update(UserSettings::volumePercents, 75).volumePercents, 75)
//        assertEquals(settings.update(UserSettings::theme, Theme.White).theme, Theme.White)
//        assertEquals(settings.update(UserSettings::theme, Theme.Black).theme, Theme.Black)
//        assertNotEquals(settings, settings.update(UserSettings::autoPlayRadio, !settings.autoPlayRadio))
//        settings.update(UserSettings::volumePercents, settings.volumePercents)
//        settings.update(UserSettings::theme, settings.theme)
    }
}