package com.fired.exchangerate

import com.fired.rate.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateInteractorImpl(private val exchangeRateRepository: ExchangeRateRepository) :
    ExchangeRateInteractor {

    override fun getExchangeRates(): Flow<List<ExchangeRate>> {
        return exchangeRateRepository
            .getExchangeRates()
            .map { it.rates }
            .map { exchangeRateModelList -> exchangeRateModelList.map { it.toExternalModel() } }
    }
}