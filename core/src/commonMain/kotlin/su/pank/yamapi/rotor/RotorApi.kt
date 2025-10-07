package su.pank.yamapi.rotor

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
