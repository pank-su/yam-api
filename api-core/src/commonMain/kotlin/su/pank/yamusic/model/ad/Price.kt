package model.ad

import kotlinx.serialization.Serializable

@Serializable
data class Price(val amount: Float, val currency: Currency)