package com.fired.api

import retrofit2.Retrofit

/**
 * @author yaya (@yahyalmh)
 * @since 25th November 2022
 */

interface RetrofitBuilder {

    val retrofit: Retrofit

    fun <T> create(service: Class<T>): T = retrofit.create(service)
}