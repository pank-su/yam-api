package model.playlist

import kotlinx.serialization.Serializable
import su.pank.yamapi.account.model.User

@Serializable
data class MadeFor(val userInfo: User, val caseForms: CaseForms)
