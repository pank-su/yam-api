package model.account

import kotlinx.serialization.Serializable

@Serializable

data class Subscription(
    val familyAutoRenewable: List<AutoRenewable>,
    val hadAnySubscription: Boolean,
    val canStartTrial: Boolean, val mcdonalds: Boolean
)