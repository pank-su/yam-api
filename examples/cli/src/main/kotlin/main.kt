import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import commands.account.Status
import commands.likes.Likes
import commands.playlist.Playlist

/**
 * Точка входа yamcli
 */
class YamCli : CliktCommand("yam") {
    override fun run() {
    }

}


fun main(args: Array<String>) = YamCli().subcommands(Status(), Likes(), Playlist()).main(args)