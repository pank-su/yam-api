package commands.likes

import com.github.ajalt.clikt.core.CliktCommand
import data.yamApiClient
import kotlinx.coroutines.runBlocking

class Likes : CliktCommand() {
    override fun run() = runBlocking {
        val client = yamApiClient()
        val likes = client.playlists.byKind(3).tracks()
        likes.forEach {
            echo("${it.title} - ${it.artists.joinToString(", ") { it.name }}")

        }
    }
}