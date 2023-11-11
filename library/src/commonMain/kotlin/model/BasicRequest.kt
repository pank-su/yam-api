package model

import kotlinx.serialization.Serializable

@Serializable
data class BasicRequest<T>(val invocationInfo: InvocationInfo, val result: T)