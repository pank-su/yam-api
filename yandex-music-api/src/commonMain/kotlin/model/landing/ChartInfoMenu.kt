package model.landing

import kotlinx.serialization.Serializable

@Serializable
data class ChartInfoMenu(val items: List<ChartInfoMenuItem>)
