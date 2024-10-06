package su.pank.yamusic.rotor.model

import kotlinx.serialization.Serializable

@Serializable
data class Dashboard(
    val dashboardId: String,
    val stations: List<StationResult>
)