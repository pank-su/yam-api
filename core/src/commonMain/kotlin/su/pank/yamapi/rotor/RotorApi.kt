package su.pank.yamapi.rotor

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.exceptions.ExperimentalYaMusicApi

@ExperimentalYaMusicApi
class RotorApi(private val client: YamApiClient) {
//    suspend fun accountStatus() = client.request<Status>("rotor", "account", "status")
//
//    suspend fun stationsDashboard() = client.request<Dashboard>("rotor", "stations", "dashboard")
//
//    suspend fun stations(language: Language = Language.ru) = client.request<List<StationResult>>(
//        listOf("rotor", "stations", "list"),
//        body = hashMapOf("language" to Json.encodeToString(language).removeCarets())
//    )
//
//    // TODO: работа с сессиями
//    suspend fun sessionNew(seeds: List<String>) =
//        client.requestPost<Session>(
//            "rotor",
//            "session",
//            "new",
//            body = hashMapOf("seeds" to "[" + seeds.joinToString(",") { "\"$it\"" } + "]",
//                "includeTracksInResponse" to "true")
//        )
}