import dsl.client
import io.getenv
import kotlinx.coroutines.runBlocking
import model.account.Permission
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class StatusTest {

    var canAuth = false

    companion object {
        var clientGoodAuth: Client? = null
        var clientBadAuth: Client? = null
    }

    @BeforeTest
    fun setUpClient() = runBlocking {
        if (clientBadAuth != null) return@runBlocking
        clientBadAuth = client { }
        val token = getenv("token") ?: return@runBlocking
        if (token == "") return@runBlocking
        clientGoodAuth = client {
            this.token = token
        }
        canAuth = true
    }

    @Test
    fun accountTest() {
        var account = clientBadAuth!!.me?.account
        assertNotNull(account?.now)
        assertNotNull(account?.region)
        assertNotNull(account?.serviceAvailable)
        if (!canAuth) {
            return
        }
        account = clientGoodAuth!!.me?.account
        assertNotNull(account?.now)
        assertNotNull(account?.region)
        assertNotNull(account?.serviceAvailable)
        assertNotNull(account?.uid)
        assertNotNull(account?.login)
        assertNotNull(account?.hostedUser)
        assertNotNull(account?.passportPhones)
        assertNotNull(account?.birthday)
        assertNotNull(account?.nonOwnerFamilyMember)
        assertNotNull(account?.child)
    }


    @Test
    fun permissionTest() {
        assertEquals(clientBadAuth?.me?.permissions?.values, clientBadAuth?.me?.permissions?.default)
        if (canAuth) assertEquals(
            clientGoodAuth?.me?.permissions?.values, setOf(
                Permission.LandingPlay,
                Permission.FeedPlay,
                Permission.RadioPlay,
                Permission.MixPlay,
                Permission.RadioSkips,
                Permission.LibraryCache,
                Permission.LibraryPlay,
                Permission.HighQuality,
                Permission.AdsSkips,
                Permission.NonShuffledPlay,
                Permission.BackgroundPlay,
                Permission.PlayPremiumTracks,
                Permission.AutoFlow,
                Permission.PlayFullTracks,
                Permission.PlayRadioFullTracks
            )
        )
    }
}