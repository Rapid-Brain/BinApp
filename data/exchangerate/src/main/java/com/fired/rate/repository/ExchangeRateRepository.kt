package com.fired.rate.repository

import com.fired.rate.model.ExchangeRateDetailModel
import com.fired.rate.model.ExchangeRatesModel


/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

interface ExchangeRateRepository {
    suspend fun getExchangeRates(): ExchangeRatesModel

    suspend fun getExchangeRate(id: String): ExchangeRateDetailModel
}