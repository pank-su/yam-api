package landing.model.chart

import kotlinx.serialization.Serializable
import track.model.Track

@Serializable
data class ChartItem(val track: Track, val chart: Chart)
