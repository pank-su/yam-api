package model.account

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result


@Serializable
data class Account(
    val now: String,
    val uid: Long? = null,
    val login: String? = null,
    val fullName: String? = null,
    val secondName: String? = null,
    val firstName: String? = null,
    val displayName: String? = null,
    val birthday: LocalDate? = null,
    val serviceAvailable: Boolean,
    val hostedUser: Boolean? = null,
    @SerialName("passport-phones") val passportPhones: List<PassportPhone> = listOf(),
    val registeredAt: Instant,
    val child: Boolean? = null,
    val nonOwnerFamilyMember: Boolean? = null,
) : Result()
