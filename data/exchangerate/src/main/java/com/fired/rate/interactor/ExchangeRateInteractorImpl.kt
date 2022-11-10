package com.fired.rate.interactor

import com.fired.core2.ext.repeatFlow
import com.fired.rate.repository.ExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateInteractorImpl @Inject constructor(private val exchangeRateRepository: ExchangeRateRepository) :
    ExchangeRateInteractor {

    override fun getRates() = flow<List<ExchangeRate>> { exchangeRates() }.flowOn(Dispatchers.IO)

    override fun getLiveRates(interval: Long) =
        repeatFlow(interval) { exchangeRates() }.flowOn(Dispatchers.IO)

    private suspend fun exchangeRates() = exchangeRateRepository.getExchangeRates()
        .rates.map { rate -> rate.toExternalModel() }
        .sortedByDescending {  it.symbol }

}