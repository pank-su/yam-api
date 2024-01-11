package model.landing

import kotlinx.serialization.Serializable

@Serializable
data class Chart(val position: Int, val progress: String, val listeners: Int, val shift: Int)
