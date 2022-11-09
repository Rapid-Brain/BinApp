package com.fired.rate.interactor

import com.fired.rate.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

class ExchangeRateInteractorImpl @Inject constructor(private val exchangeRateRepository: ExchangeRateRepository) :
    ExchangeRateInteractor {

    override fun getExchangeRates() =
        exchangeRateRepository
            .getExchangeRates()
            .map { it.rates }
            .map { exchangeRateModelList -> exchangeRateModelList.map { it.toExternalModel() } }
}