package landing.model.chart

import kotlinx.serialization.Serializable
import track.model.TrackDTO

@Serializable
data class ChartItem(val track: TrackDTO, val chart: Chart)
