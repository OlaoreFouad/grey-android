package dev.olaore.data.util

import android.util.Log
import java.io.IOException
import retrofit2.HttpException
import dev.olaore.domain.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeNetworkCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    networkCall: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(networkCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    Result.GenericError(code, errorResponse)
                }
                else -> {
                    Result.GenericError(null, throwable.message)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.message()
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}
