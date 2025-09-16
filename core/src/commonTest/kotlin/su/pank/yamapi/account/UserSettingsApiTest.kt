
package su.pank.yamapi.account

import kotlinx.coroutines.test.runTest
import su.pank.yamapi.account.model.Theme
import su.pank.yamapi.account.model.UserSettings
import su.pank.yamapi.account.model.Visibility
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class UserSettingsApiTest {

    @OptIn(ExperimentalTime::class)
    @Test
    fun testSettings() = runTest {
        val expectedUserSettings = UserSettings(
            uid = 12345,
            lastFmScrobblingEnabled = true,
            facebookScrobblingEnabled = false,
            shuffleEnabled = true,
            addNewTrackOnPlaylistTop = false,
            volumePercents = 80,
            userMusicVisibility = Visibility.PUBLIC,
            userSocialVisibility = Visibility.PRIVATE,
            modified = Instant.parse("2025-09-16T12:00:00Z"),
            theme = Theme.Black,
            promosDisabled = true,
            autoPlayRadio = false,
            syncQueueEnabled = true,
            childModEnabled = false,
            adsDisabled = true
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedUserSettings))

        val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
        val accountApi = AccountApi(yamApiClient)

        val actualUserSettings = accountApi.settings()

        assertEquals(expectedUserSettings, actualUserSettings)
    }
}
