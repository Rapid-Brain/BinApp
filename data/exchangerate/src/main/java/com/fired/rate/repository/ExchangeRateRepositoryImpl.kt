package com.fired.rate.repository

import com.fired.core2.ext.repeatFlow
import com.fired.rate.api.ExchangeRateApi
import com.fired.rate.model.ExchangeRatesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateRepositoryImpl @Inject constructor(private val exchangeRateApi: ExchangeRateApi) :
    ExchangeRateRepository {

    override fun getExchangeRates(): Flow<ExchangeRatesModel> = repeatFlow(3000) {
        exchangeRateApi.getExchangeRates()
    }.flowOn(Dispatchers.IO)

}
