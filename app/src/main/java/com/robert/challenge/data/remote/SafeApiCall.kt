package com.robert.challenge.data.remote

import kotlinx.coroutines.delay
import okio.IOException

suspend fun <T> safeApiCallWithRetry(
    maxRetries: Int = 3,
    initialDelayMillis: Long = 1000,
    maxDelayMillis: Long = 5000,
    apiCall: suspend () -> T
): ApiResult<T> {
    var currentAttempt = 0
    var currentDelay = initialDelayMillis

    while (currentAttempt < maxRetries) {
        try {
            return ApiResult.Success(apiCall())
        } catch (e: IOException) { // Retry on network-related exceptions
            currentAttempt++
            if (currentAttempt >= maxRetries) {
                return ApiResult.Error(e)
            }
            delay(currentDelay)
            currentDelay = (currentDelay * 2).coerceAtMost(maxDelayMillis) // Exponential backoff
        } catch (e: Exception) {
            return ApiResult.Error(e) // Do not retry on other exceptions
        }
    }

    return ApiResult.Error(Exception("Max retry limit reached"))
}
