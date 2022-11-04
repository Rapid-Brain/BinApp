package com.fired.rate.repository

import com.fired.rate.model.ExchangeRates
import kotlinx.coroutines.flow.Flow


/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

interface ExchangeRateRepository {

    fun getExchangeRates(): Flow<ExchangeRates>
}