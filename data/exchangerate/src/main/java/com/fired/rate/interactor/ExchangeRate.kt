package com.fired.rate.interactor

import com.fired.rate.model.ExchangeRateModel
import java.math.BigDecimal

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

data class ExchangeRate(
    val id: String,
    val symbol: String,
    val currencySymbol: String?,
    val type: String,
    val rateUsd: BigDecimal
)

fun ExchangeRateModel.toExternalModel() = ExchangeRate(
    id = id,
    symbol = symbol,
    currencySymbol = currencySymbol,
    type = type,
    rateUsd = BigDecimal(rateUsd)
)
