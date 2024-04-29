package model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

/**
 * Статус пользователя, то есть имеет ли он подписки, права и т.п.
 *
 * @param account аккаунт пользователя
 * @param permissions права пользователя
 * @param subscription подписка пользователя
 *
 * @see Account
 * @see Permissions
 * @see Subscription
 */

@Serializable
data class Status(
    val account: Account,
    val permissions: Permissions,
    val subscription: Subscription? = null,
    @SerialName("bar-below") val barBellow: BarBellow? = null,
    @SerialName("subeditor") val isSubEditor: Boolean? = null,
    val subeditorLevel: Int? = null,
    val pretrialActive: Boolean? = null,
    val plus: Plus? = null,
    val defaultEmail: String? = null,
    @SerialName("userhash") val userHash: String? = null
) : Result()