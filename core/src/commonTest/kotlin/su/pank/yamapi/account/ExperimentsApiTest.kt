
package su.pank.yamapi.account

import kotlinx.coroutines.test.runTest
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class ExperimentsApiTest {
    @Test
    fun testExperiments() = runTest {
        val expectedExperiments = hashMapOf(
            "experiment1" to "value1",
            "experiment2" to "value2"
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedExperiments))

        val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
        val accountApi = AccountApi(yamApiClient)

        val actualExperiments = accountApi.experiments()

        assertEquals(expectedExperiments, actualExperiments)
    }
}
