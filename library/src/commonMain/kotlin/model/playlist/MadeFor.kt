package model.playlist

import kotlinx.serialization.Serializable
import model.account.User

@Serializable
data class MadeFor(val userInfo: User, val caseForms: CaseForms)
