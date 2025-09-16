
package su.pank.yamapi.account

import kotlinx.coroutines.test.runTest
import model.PermissionAlerts
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class PermissionAlertsApiTest {
    @Test
    fun testPermissionAlerts() = runTest {
        val expectedPermissionAlerts = PermissionAlerts(
            alerts = listOf("alert1", "alert2", "alert3")
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedPermissionAlerts))

        val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
        val accountApi = AccountApi(yamApiClient)

        val actualPermissionAlerts = accountApi.permissionAlerts()

        assertEquals(expectedPermissionAlerts, actualPermissionAlerts)
    }
}
