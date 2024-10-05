package su.pank.yamusic.account.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import su.pank.yamusic.account.model.AutoRenewable

@Serializable

data class Subscription(
    val familyAutoRenewable: List<AutoRenewable>? = null,
    val hadAnySubscription: Boolean? = null,
    val canStartTrial: Boolean? = null, val mcdonalds: Boolean? = null,
    val end: Instant? = null
)