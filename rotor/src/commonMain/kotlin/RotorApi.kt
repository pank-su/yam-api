

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.exceptions.ExperimentalYamApi

/**
 * API для работы с ротором (радио).
 *
 * @param client Клиент YamApiClient.
 */
@ExperimentalYamApi
class RotorApi(
    private val client: YamApiClient,
)

@ExperimentalYamApi
context(client: YamApiClient)
val rotor: RotorApi get() = RotorApi(client)
