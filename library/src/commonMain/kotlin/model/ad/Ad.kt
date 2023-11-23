package model.ad

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

@Serializable
enum class TypeProduct {
    @SerialName("subscription")
    Subscription
}

enum class Currency {
    RUB
}

@Serializable
enum class PaymentMethod {
    @SerialName("card")
    Card
}

@Serializable
data class Ad(
    val offersBatchId: String? = null,
    val inAppProducts: List<AppProduct>,
    val nativeProducts: List<AppProduct>,
    val webPaymentUrl: String,
    val promoCodesEnabled: Boolean
) :
    Result()
