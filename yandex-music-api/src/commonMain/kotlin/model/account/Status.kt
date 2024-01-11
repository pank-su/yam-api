package model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result


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