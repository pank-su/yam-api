package rotor

import Client
import account.model.Status
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Language
import rotor.model.Dashboard
import rotor.model.Session
import rotor.model.StationResult
import utils.removeCarets

class RotorApi(private val client: Client) {
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