package model.account

import kotlinx.serialization.Serializable

@Serializable

data class Subscription(val familyAutoRenewable: List<AutoRenewable>)