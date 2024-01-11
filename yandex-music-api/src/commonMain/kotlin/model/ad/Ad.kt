package model.ad

import kotlinx.serialization.Serializable
import model.Result

@Serializable
data class Ad(
    val offersBatchId: String? = null,
    val inAppProducts: List<AppProduct>,
    val nativeProducts: List<AppProduct>,
    val webPaymentUrl: String,
    val promoCodesEnabled: Boolean
) :
    Result()
