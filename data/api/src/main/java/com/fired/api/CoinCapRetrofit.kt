package com.fired.api

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 25th November 2022
 */

class CoinCapRetrofit @Inject constructor(builder: Retrofit.Builder) : RetrofitBuilder {
    companion object {
        private const val COINCAP_BASE_URL = "https://api.coincap.io/"
    }

    override val retrofit: Retrofit = builder.baseUrl(COINCAP_BASE_URL).build()
}