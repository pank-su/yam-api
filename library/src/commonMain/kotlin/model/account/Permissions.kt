package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable


@Serializable
data class Permissions(val until: Instant, val values: Set<Permission>, val default: Set<Permission>)
