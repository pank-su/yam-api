package model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

/**
 * Статус промокода после использования
 * @param status статус промокода
 * @param description описание статуса
 * @param accountStatus статус аккаунта
 *
 * @see Status
 */

// TODO найти все статусы и преобразовать их в enum
@Serializable
data class PromoCodeStatus(
    val status: String,
    @SerialName("statusDesc") val description: String,
    val accountStatus: Status
) : Result()
