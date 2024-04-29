package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

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
