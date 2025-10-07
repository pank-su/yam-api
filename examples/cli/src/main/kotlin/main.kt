import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import data.yamApiClient
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime

/**
 * Получение статуса
 */
class Status : CliktCommand() {
    @OptIn(ExperimentalTime::class)
    override fun run() =
        runBlocking {
            val yamApiClient = yamApiClient()
            val status = yamApiClient.account.status()
            echo("Хеш пользователя: ${status.userHash}")
            echo("Почта по умолчанию: ${status.defaultEmail}")
            echo("Плюс: ${status.plus?.hasPlus.toYesOrNo()}")
            echo("Подписка: ${(status.subscription?.end?.let { "до $it" }) ?: "Нет"}")

        }

    private fun Boolean?.toYesOrNo(): String = if (this == true) "Да" else "Нет"


}

/**
 * Точка входа yamcli
 */
class YamCli : CliktCommand("yamcl") {
    override fun run() {
    }

}


fun main(args: Array<String>) = YamCli().subcommands(Status()).main(args)