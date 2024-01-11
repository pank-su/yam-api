package model.feed

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class Feed(
    val pumpkin: Boolean,
    val canGetMoreEvents: Boolean,
    val isWizardPassed: Boolean,
    val generatedPlaylists: List<GeneratedPlaylist>,
    val today: LocalDate,
    val days: List<Day>
) : Result()
