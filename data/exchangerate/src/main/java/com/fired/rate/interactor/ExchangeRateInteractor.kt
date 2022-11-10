package com.fired.rate.interactor

import kotlinx.coroutines.flow.Flow

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

interface ExchangeRateInteractor {
    fun getRates(): Flow<List<ExchangeRate>>
    fun getLiveRates(interval: Long): Flow<List<ExchangeRate>>
    fun getLiveRate(id: String, interval: Long = 3000L): Flow<ExchangeDetailRate>
}