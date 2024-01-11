package model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

// TODO найти все статусы и преобразовать их в enum
@Serializable
data class PromoCodeStatus(
    val status: String,
    @SerialName("statusDesc") val description: String,
    val accountStatus: Status
) : Result()
