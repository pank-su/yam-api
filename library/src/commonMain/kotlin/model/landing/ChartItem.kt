package model.landing

import kotlinx.serialization.Serializable
import model.track.Track

@Serializable
data class ChartItem(val track: Track, val chart: Chart)
