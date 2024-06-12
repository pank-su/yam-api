package rotor

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import account.model.Permission
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountStatusTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.rotor.accountStatus()
    }

    @Test
    fun checkPermission() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        assertEquals(
            client { this.token = token!! }.rotor.accountStatus().permissions.values,
            setOf(Permission.RadioSkips)
        )
    }
}