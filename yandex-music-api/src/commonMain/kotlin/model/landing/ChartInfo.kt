package model.landing

import kotlinx.serialization.Serializable
import model.Result
import model.playlist.Playlist

@Serializable
data class ChartInfo(val id: String, val type: String, val typeForFrom: String, val title: String, val menu: ChartInfoMenu, val chart: Playlist? = null, val chartDescription: String? = null) : Result()
