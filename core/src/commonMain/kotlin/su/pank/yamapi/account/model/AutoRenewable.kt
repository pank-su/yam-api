package su.pank.yamapi.account.model

import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Описание подписки
 *
 * @param expires когда истекает подписка
 * @param vendor что за подписка
 * @param vendorHelpUrl ссылка для помощи по подписке
 * @param masterInfo TODO
 * @param productId ID продукта
 * @param orderId ID заказа
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class AutoRenewable(
    val expires: Instant,
    val vendor: String,
    val vendorHelpUrl: String,
    val finished: Boolean,
    val masterInfo: User? = null,
    val productId: String? = null,
    val orderId: Int? = null
)
