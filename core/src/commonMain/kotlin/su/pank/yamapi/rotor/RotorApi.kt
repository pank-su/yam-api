package su.pank.yamapi.rotor

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.exceptions.ExperimentalYaMusicApi

/**
 * API для работы с ротором (радио).
 *
 * @param client Клиент YamApiClient.
 */
@ExperimentalYaMusicApi
class RotorApi(
    private val client: YamApiClient,
)
