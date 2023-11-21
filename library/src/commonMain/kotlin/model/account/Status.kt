package model.account

import kotlinx.serialization.Serializable
import model.Result


@Serializable
data class Status(val account: Account, val permissions: Permissions, val subscription: Subscription): Result()