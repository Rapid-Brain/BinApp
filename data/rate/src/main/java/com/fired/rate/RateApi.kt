package com.fired.rate

import com.fired.rate.model.Rates
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

interface RateApi {

    @GET("rate/v2")
    fun getRates(): Flow<Rates>
}