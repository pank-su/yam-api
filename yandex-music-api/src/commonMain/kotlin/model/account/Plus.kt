package model.account

import kotlinx.serialization.Serializable

@Serializable
data class Plus(val hasPlus: Boolean, val isTutorialCompleted: Boolean)
