package com.fired.exchangerate

import kotlinx.coroutines.flow.Flow

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

interface ExchangeRateInteractor {
    fun getExchangeRates(): Flow<List<ExchangeRate>>
}