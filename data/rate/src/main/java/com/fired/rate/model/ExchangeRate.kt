package com.fired.rate.model

import com.google.gson.annotations.SerializedName

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

data class ExchangeRate(
    val id: String,
    val symbol: String,
    val currencySymbol: String,
    val type: String,
    val rateUsd: String
)

data class ExchangeRates(
    @SerializedName("data")
    val rates: List<ExchangeRate>
)