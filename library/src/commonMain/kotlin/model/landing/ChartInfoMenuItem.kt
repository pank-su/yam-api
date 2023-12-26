package model.landing

import kotlinx.serialization.Serializable

@Serializable
data class ChartInfoMenuItem(val title: String, val url: String, val selected: Boolean? = null)
