package com.example.bisky.data.network.resultwrapper

import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

class ResultWrapper @Inject constructor() {
    @SuppressWarnings("TooGenericExceptionCaught")
    suspend fun <R> wrap(request: suspend () -> R): Result<R, Throwable> {
        val result = try {
            Result.Success(request.invoke().also { Timber.i(it.toString()) })
        } catch (e: Throwable) {
            Timber.e(e)
            Result.Error(wrapError(e))
        }
        return result
    }

    private fun wrapError(error: Throwable): Throwable {
        return when (error) {
            is HttpException -> {
                when (error.code()) {
                    else -> {
                        val data = error.response()?.errorBody()?.string().orEmpty()
                        PayloadApiException(data)
                    }
                }
            }
            is SocketTimeoutException -> ServerOverloadException
            else -> error
        }
    }
}
