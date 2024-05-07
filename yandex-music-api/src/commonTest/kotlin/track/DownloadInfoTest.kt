package track

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlin.test.Test

class DownloadInfoTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client { this.token = token!! }
        client.tracksDownloadInfo("1a9d4eff-a645-44a6-aa31-5ea7e368714a")
        val bytes = client.tracksDownloadInfo("1a9d4eff-a645-44a6-aa31-5ea7e368714a").value!![1].download(client)
        SystemFileSystem.sink(Path("/home/panksu/test.mp3")).buffered().write(bytes)
    }

    @Test
    fun parsingDataFromNewIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client { this.token = token!! }
        client.tracksDownloadInfoNew(18373917, true)
    }
}
