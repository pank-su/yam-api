package account.model

import kotlinx.serialization.Serializable

/**
 * Описание номеров телефона пользователя
 * @param phone Номер телефона пользователя
 *
 * @see Account
 */
@Serializable
data class PassportPhone(val phone: String)