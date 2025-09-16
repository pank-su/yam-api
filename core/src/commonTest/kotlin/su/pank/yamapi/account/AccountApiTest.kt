@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.account

import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import su.pank.yamapi.account.model.*
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class AccountApiTest {
    @Test
    fun testStatus() = runTest {
        val expectedAccount = Account(
            now = Instant.parse("2025-09-16T12:00:00Z"),
            uid = 12345,
            login = "testuser",
            fullName = "Test User",
            secondName = "User",
            firstName = "Test",
            displayName = "Test",
            birthday = kotlinx.datetime.LocalDate.parse("2000-01-01"),
            serviceAvailable = true
        )

        val expectedPermissions = Permissions(
            until = Instant.parse("2025-12-31T23:59:59Z"),
            values = setOf(Permission.LandingPlay, Permission.FeedPlay),
            default = setOf(Permission.RadioPlay)
        )

        val expectedStatus = Status(
            account = expectedAccount,
            permissions = expectedPermissions
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedStatus))

        val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
        val accountApi = AccountApi(yamApiClient)

        val actualStatus = accountApi.status()

        assertEquals(expectedStatus, actualStatus)
    }

    @Test
    fun testConsumePromoCode() = runTest {
        val expectedAccount = Account(
            now = Instant.parse("2025-09-16T12:00:00Z"),
            uid = 12345,
            login = "testuser",
            fullName = "Test User",
            secondName = "User",
            firstName = "Test",
            displayName = "Test",
            birthday = kotlinx.datetime.LocalDate.parse("2000-01-01"),
            serviceAvailable = true
        )

        val expectedPermissions = Permissions(
            until = Instant.parse("2025-12-31T23:59:59Z"),
            values = setOf(Permission.LandingPlay, Permission.FeedPlay),
            default = setOf(Permission.RadioPlay)
        )

        val expectedStatus = Status(
            account = expectedAccount,
            permissions = expectedPermissions
        )

        val expectedPromoCodeStatus = PromoCodeStatus(
            status = "success",
            description = "Promo code activated",
            accountStatus = expectedStatus
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedPromoCodeStatus))

        val yamApiClient = createMockedYamApiClient {
            assertEquals("/account/consume-promo-code", it.url.encodedPath)
            assertTrue(it.body is FormDataContent)

            assertEquals(Parameters.build { append("code", "test-code"); append("language", "ru") }, (it.body as FormDataContent).formData)
            mockJsonResponse(responseJson)(this, it)
        }
        val accountApi = AccountApi(yamApiClient)

        val actualPromoCodeStatus = accountApi.consumePromoCode("test-code")

        assertEquals(expectedPromoCodeStatus, actualPromoCodeStatus)
    }
}
