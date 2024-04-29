package rotor

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import model.account.Permission
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountStatusTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.rotorAccountStatus()
    }

    @Test
    fun checkPermission() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        assertEquals(
            client { this.token = token!! }.rotorAccountStatus().permissions.values,
            setOf(Permission.RadioSkips)
        )
    }
}