package com.fired.rate.interactor

import com.fired.rate.model.ExchangeRateDetailModel
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

data class ExchangeDetailRate(
    val id: String,
    val symbol: String,
    val currencySymbol: String?,
    val type: String,
    val rateUsd: BigDecimal,
    val timestamp: Long
)

fun ExchangeRateModel.toExternalModel() = ExchangeRate(
    id = id,
    symbol = symbol,
    currencySymbol = currencySymbol,
    type = type,
    rateUsd = BigDecimal(rateUsd)
)

fun ExchangeRateDetailModel.toExternalModel() = ExchangeDetailRate(
    id = rateDetail.id,
    symbol = rateDetail.symbol,
    currencySymbol = rateDetail.currencySymbol,
    type = rateDetail.type,
    rateUsd = BigDecimal(rateDetail.rateUsd),
    timestamp = timestamp.toLong()
)
