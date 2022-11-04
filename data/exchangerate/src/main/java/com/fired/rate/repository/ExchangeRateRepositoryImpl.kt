package com.fired.rate.repository

import com.fired.rate.api.ExchangeRateApi
import com.fired.rate.model.ExchangeRatesModel
import kotlinx.coroutines.flow.Flow

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateRepositoryImpl(private val exchangeRateApi: ExchangeRateApi) :
    ExchangeRateRepository {

    override fun getExchangeRates(): Flow<ExchangeRatesModel> = exchangeRateApi.getExchangeRates()
}