package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable

data class AutoRenewable(
    val expires: Instant,
    val vendor: String,
    val vendorHelpUrl: String,
    val masterInfo: User? = null,
    val finished: Boolean,
    val productId: String? = null,
    val orderId: Int? = null
)
