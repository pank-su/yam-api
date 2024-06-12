package landing.model.chart

import kotlinx.serialization.Serializable

@Serializable
data class ChartInfoMenu(val items: List<ChartInfoMenuItem>)
