package model.ad

import kotlinx.datetime.DateTimePeriod
import kotlinx.serialization.Serializable

@Serializable
data class AppProduct(
    val productId: String,
    val offersPositionId: String,
    val type: TypeProduct,
    val commonPeriodDuration: DateTimePeriod? = null,
    val duration: Int,
    val trialDuration: Int,
    val price: Price? = null,
    val plus: Boolean,
    val feature: String,
    val features: List<Feature>,
    val debug: Boolean = false,
    val buttonText: String = "",
    val buttonAdditionalText: String = "",
    val description: String,
    val paymentMethodTypes: List<PaymentMethod>,
    val available: Boolean,
    val vendorTrialAvailable: Boolean? = null,
    val familySub: Boolean? = null,
    val trialAvailable: Boolean? = null,
    val family: Boolean? = null
)