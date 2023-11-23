package model

import kotlinx.serialization.Serializable

@Serializable
data class PermissionAlerts(val alerts: List<String>): Result()