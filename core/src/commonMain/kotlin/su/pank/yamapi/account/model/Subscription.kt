package su.pank.yamapi.account.model

import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable

data class Subscription(
    val familyAutoRenewable: List<AutoRenewable>? = null,
    val hadAnySubscription: Boolean? = null,
    val canStartTrial: Boolean? = null, val mcdonalds: Boolean? = null,
    val end: Instant? = null
)