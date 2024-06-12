package account.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * Права пользователя
 *
 * @param until до какого времени действуют права
 * @param values права
 * @param default права по умолчанию (права пользователя без подписки)
 *
 * @see Permission
 */

@Serializable
data class Permissions(val until: Instant, val values: Set<Permission>, val default: Set<Permission>)
