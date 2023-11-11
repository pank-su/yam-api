package model.account

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val now: String,
    val uid: String?,
    val login: String?,
    val fullName: String?,
    val secondName: String?,
    val firstName: String?
)
