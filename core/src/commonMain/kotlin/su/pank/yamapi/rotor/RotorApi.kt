package su.pank.yamapi.rotor

import kotlinx.serialization.json.Json
import su.pank.yamapi.YaMusicApiClient
import su.pank.yamapi.account.model.Status
import su.pank.yamapi.exceptions.ExperimentalYaMusicApi
import su.pank.yamapi.model.Language
import su.pank.yamapi.rotor.model.Dashboard
import su.pank.yamapi.rotor.model.Session
import su.pank.yamapi.rotor.model.StationResult
import su.pank.yamapi.utils.removeCarets

@ExperimentalYaMusicApi
class RotorApi(private val client: YaMusicApiClient) {
    suspend fun accountStatus() = client.request<Status>("rotor", "account", "status")

    suspend fun stationsDashboard() = client.request<Dashboard>("rotor", "stations", "dashboard")

    suspend fun stations(language: Language = Language.ru) = client.request<List<StationResult>>(
        listOf("rotor", "stations", "list"),
        body = hashMapOf("language" to Json.encodeToString(language).removeCarets())
    )

    // TODO: работа с сессиями
    suspend fun sessionNew(seeds: List<String>) =
        client.requestPost<Session>(
            "rotor",
            "session",
            "new",
            body = hashMapOf("seeds" to "[" + seeds.joinToString(",") { "\"$it\"" } + "]",
                "includeTracksInResponse" to "true")
        )
}