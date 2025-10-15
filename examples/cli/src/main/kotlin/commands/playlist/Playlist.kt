package commands.playlist

import com.github.ajalt.clikt.core.CliktCommand
import data.yamApiClient
import kotlinx.coroutines.runBlocking

class Playlist: CliktCommand() {
    override fun run() = runBlocking{
        val yamApiClient = yamApiClient()
        val playlist = yamApiClient.albumsWithTracks(4193502)
        val shortTracks = playlist.volumes?.first()
        val tracks = yamApiClient.tracks(*(shortTracks!!.map { it.id }.toTypedArray()))
        tracks.forEach {
            echo(it)
        }
    }
}