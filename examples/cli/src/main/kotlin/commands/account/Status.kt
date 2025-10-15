package commands.account

import com.github.ajalt.clikt.core.CliktCommand
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

            echo("ID пользователя: ${status.account.uid}")
            echo("Почта: ${status.defaultEmail}")
            echo("Подписка: ${status.hasSubscription.toYesOrNo()}")
        }

    private fun Boolean?.toYesOrNo(): String = if (this == true) "Да" else "Нет"
}