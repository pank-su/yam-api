package model.account

import kotlinx.serialization.Serializable

@Serializable

data class User(
    val uid: Int,
    val login: String,
    val name: String? = null,
    val displayName: String? = null,
    val sex: String? = null,
    val regions: List<Int> = listOf()
)