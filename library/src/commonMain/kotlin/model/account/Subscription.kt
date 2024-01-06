package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable

data class Subscription(
    val familyAutoRenewable: List<AutoRenewable>? = null,
    val hadAnySubscription: Boolean? = null,
    val canStartTrial: Boolean? = null, val mcdonalds: Boolean? = null,
    val end: Instant? = null
)