package com.fired.core2.ext

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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