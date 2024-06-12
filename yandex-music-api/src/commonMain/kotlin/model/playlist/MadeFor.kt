package model.playlist

import kotlinx.serialization.Serializable
import account.model.User

@Serializable
data class MadeFor(val userInfo: User, val caseForms: CaseForms)
