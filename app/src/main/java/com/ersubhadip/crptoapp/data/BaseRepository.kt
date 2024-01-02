package com.ersubhadip.crptoapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    //Wrapper to safe call retry() in IO with 3 times by default
    suspend fun <T> retryIO(
        times: Int = 3,
        initialDelayMillis: Long = 100,
        maxDelayMillis: Long = 1000,
        factor: Double = 2.0,
        block: suspend () -> T
    ): T = retry(times, initialDelayMillis, maxDelayMillis, factor) {
        withContext(Dispatchers.IO) {
            block()
        }
    }

    //Wrapper to call action with retry
    private suspend fun <T> retry(
        times: Int,
        initialDelayMillis: Long,
        maxDelayMillis: Long,
        factor: Double,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelayMillis
        repeat(times - 1) {
            try {
                return block()
            } catch (e: Exception) {
                delay(currentDelay)
                currentDelay =
                    (currentDelay * factor).coerceAtMost(maxDelayMillis.toDouble()).toLong()
            }
        }
        return block()
    }

    //Wrapper for try-catch
    suspend fun <T> safeWrap(
        block: suspend () -> T,
        errorHandler: suspend (Exception) -> T
    ): T {
        return try {
            block()
        } catch (e: Exception) {
            errorHandler(e)
        }
    }
}