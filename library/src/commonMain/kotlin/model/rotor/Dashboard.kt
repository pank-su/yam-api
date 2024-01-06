package model.rotor

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class Dashboard(
    val dashboardId: String,
    val stations: List<StationResult>
) : Result()
