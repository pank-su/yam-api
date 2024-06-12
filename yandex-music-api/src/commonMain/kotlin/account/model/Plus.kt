package account.model

import kotlinx.serialization.Serializable

/**
 * Описание наличия подписки у пользователя
 */

@Serializable
data class Plus(val hasPlus: Boolean, val isTutorialCompleted: Boolean)
