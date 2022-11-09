package com.fired.rate.api

import com.fired.rate.model.ExchangeRatesModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

interface ExchangeRateApi {

    @GET("v2/rates")
    suspend fun getExchangeRates(): ExchangeRatesModel
}