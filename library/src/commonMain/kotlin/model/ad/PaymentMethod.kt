package model.ad

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PaymentMethod {
    @SerialName("card")
    Card
}