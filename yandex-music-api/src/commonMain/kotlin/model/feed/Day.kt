package model.feed

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Day(val day: LocalDate, val events: List<Event>)
