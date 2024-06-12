package account.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Аккаунт пользователя
 *
 * @param now время получения
 * @param uid уникальный идентификатор пользователя
 * @param login логин пользователь
 * @param fullName полное имя пользователя
 * @param secondName фамилия пользователя
 * @param displayName отображаемое имя пользователя
 * @param birthday день рождения пользователя
 * @param serviceAvailable доступен ли сервис для пользователя
 * @param hostedUser TODO
 * @param registeredAt время регистрации
 * @param child является ли пользователь ребёнком
 * @param passportPhones телефоны пользователя
 * @param nonOwnerFamilyMember является ли пользователь главой семью
 * @param region регион пользователя
 *
 * @see PassportPhone
 */
@Serializable
data class Account(
    val now: Instant,
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
    val registeredAt: Instant? = null,
    val child: Boolean? = null,
    val nonOwnerFamilyMember: Boolean? = null,
    val region: Int? = null
)
