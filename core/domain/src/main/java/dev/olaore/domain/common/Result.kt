package dev.olaore.domain.common

sealed class Result <out T> {

    data class Success<T>(val data: T): Result<T>()

    data class GenericError(
        val code: Int? = null,
        val response: String?
    ) : Result<Nothing>()

    object NetworkError : Result<Nothing>()
}
