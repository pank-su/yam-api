package su.pank.yamapi.account.model

import kotlinx.serialization.Serializable

@Serializable
data class PermissionAlerts(
    val alerts: List<String>,
)
