package model

import Client
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class Result {
    @Transient var invocationInfo: InvocationInfo? = null
    @Transient var client: Client? = null
}

@Serializable
open class ResultPrimitive<T>() {
    var value: T? = null
    @Transient
    var invocationInfo: InvocationInfo? = null
    @Transient
    var client: Client? = null

    override fun toString(): String {
        return value.toString()
    }
}

@Serializable
class ResultList<T> : List<T> by listOf(), Result()


    @Serializable
data class BasicResponse<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T? = null,
    @SerialName("error") private val _error: Error? = null
) where T : Result {
    val result: T
        get() {
            if (_result == null) {
                errorTypeToException[_error?.type]!!(_error?.message ?: "Неизвестна ошибка")
                throw Throwable()
            }
            return _result.apply {
                this.invocationInfo = this@BasicResponse.invocationInfo
            }
        }
}

@Serializable
data class BasicResponsePrimitive<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T? = null,
    @SerialName("error") private val _error: Error? = null
) {
    val result: ResultPrimitive<T>
        get() {
            if (_result == null) {
                errorTypeToException[_error?.type]!!(_error?.message ?: "Неизвестна ошибка")
                throw Throwable()
            }

            val result = ResultPrimitive<T>()

            return result.apply {
                value = this@BasicResponsePrimitive._result
                this.invocationInfo = this@BasicResponsePrimitive.invocationInfo
            }
        }
}