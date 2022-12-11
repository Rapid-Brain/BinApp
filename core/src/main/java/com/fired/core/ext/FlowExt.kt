package com.fired.core.ext

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

/**
 * @author yaya (@yahyalmh)
 * @since 09th November 2022
 */

fun <T> repeatFlow(interval: Long, block: suspend () -> T): Flow<T> =
    flow {
        while (true) {
            emit(block.invoke())
            delay(interval)
        }
    }

fun <T> Flow<T>.retryWithPolicy(
    retryPolicy: RetryPolicy = RetryPolicy.DefaultRetryPolicy,
    retryHandler: () -> Unit
): Flow<T> {
    var currentDelay = retryPolicy.delayMillis

    return retryWhen { cause, attempt ->
        retryHandler()
        if (cause is IOException && attempt < retryPolicy.numRetries) {
            delay(currentDelay)
            currentDelay *= retryPolicy.delayFactor
            return@retryWhen true
        } else {
            return@retryWhen false
        }
    }
}

sealed class RetryPolicy(
    val numRetries: Long,
    val delayMillis: Long,
    val delayFactor: Long
) {
    object DefaultRetryPolicy : RetryPolicy(numRetries = 6, delayMillis = 1000, delayFactor = 1)
}