package com.fired.rate.repository

import com.fired.rate.api.ExchangeRateApi
import com.fired.rate.model.ExchangeRateDetailModel
import com.fired.rate.model.ExchangeRatesModel
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateRepositoryImpl @Inject constructor(private val exchangeRateApi: ExchangeRateApi) :
    ExchangeRateRepository {

    override suspend fun getExchangeRates(): ExchangeRatesModel = exchangeRateApi.getExchangeRates()

    override suspend fun getExchangeRate(id: String): ExchangeRateDetailModel =
        exchangeRateApi.getExchangeRate(id)
}
