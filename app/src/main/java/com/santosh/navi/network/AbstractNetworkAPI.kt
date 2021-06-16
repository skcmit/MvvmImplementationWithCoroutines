package com.santosh.navi.network

import com.santosh.navi.util.Result
import retrofit2.Response

abstract class AbstractNetworkAPI {
    suspend fun <T> getResult(suspendFunction0: suspend () -> Response<T>): Result<T> {
        return try {
            val response = suspendFunction0()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) } ?: Result.error(response.message())
            } else {
                Result.error(response.message())
            }
        } catch (exception: Exception) {
            Result.error(exception.message)
        }
    }
}