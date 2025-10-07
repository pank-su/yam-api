import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import commands.account.Status

/**
 * Точка входа yamcli
 */
class YamCli : CliktCommand("yam") {
    override fun run() {
    }

}


fun main(args: Array<String>) = YamCli().subcommands(Status()).main(args)