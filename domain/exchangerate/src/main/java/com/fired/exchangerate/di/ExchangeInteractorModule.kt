package com.fired.exchangerate.di

import com.fired.exchangerate.ExchangeRateInteractor
import com.fired.exchangerate.ExchangeRateInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author yaya (@yahyalmh)
 * @since 04th November 2022
 */

@Module
@InstallIn(SingletonComponent::class)
interface ExchangeInteractorModule {

    @Binds
    fun bindExchangeInteractor(exchangeRateInteractor: ExchangeRateInteractorImpl): ExchangeRateInteractor
}