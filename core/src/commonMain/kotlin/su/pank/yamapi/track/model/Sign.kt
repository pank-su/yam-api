package su.pank.yamapi.track.model

import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Serializable
data class Sign(val timestamp: Long, val value: String)
