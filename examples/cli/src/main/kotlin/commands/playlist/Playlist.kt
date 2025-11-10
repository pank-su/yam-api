package commands.playlist

import com.github.ajalt.clikt.core.CliktCommand
import data.yamApiClient
import kotlinx.coroutines.runBlocking

class Playlist : CliktCommand() {
    override fun run() = runBlocking {
        val client = yamApiClient()

        val playlist = client.playlists.byKinds(1009)
        client.playlists.unlike(playlist.first().id)
        echo(playlist)
    }
}