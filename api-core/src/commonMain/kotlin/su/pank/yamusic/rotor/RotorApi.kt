package su.pank.yamusic.rotor

import su.pank.yamusic.account.model.Status
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Language
import su.pank.yamusic.rotor.model.Dashboard
import su.pank.yamusic.rotor.model.Session
import su.pank.yamusic.YaMusicApiClient
import su.pank.yamusic.exceptions.ExperimentalYaMusicApi
import su.pank.yamusic.rotor.model.StationResult
import su.pank.yamusic.utils.removeCarets

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