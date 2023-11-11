package model.account

import kotlinx.serialization.Serializable

@Serializable
data class Status(val account: Account)