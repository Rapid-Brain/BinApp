package com.fired.rate.di

import com.fired.api.CoinCapRetrofit
import com.fired.rate.api.ExchangeRateApi
import com.fired.rate.interactor.ExchangeRateInteractor
import com.fired.rate.interactor.ExchangeRateInteractorImpl
import com.fired.rate.repository.ExchangeRateRepository
import com.fired.rate.repository.ExchangeRateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */
@Module
@InstallIn(SingletonComponent::class)
interface ExchangeRateModule {

    companion object {
        @Provides
        @Singleton
        fun provideExchangeRateApi(coinCapRetrofit: CoinCapRetrofit): ExchangeRateApi =
            coinCapRetrofit.create(ExchangeRateApi::class.java)
    }

    @Binds
    @Singleton
    fun bindExchangeRateRepository(repository: ExchangeRateRepositoryImpl): ExchangeRateRepository

    @Binds
    fun bindExchangeInteractor(exchangeRateInteractor: ExchangeRateInteractorImpl): ExchangeRateInteractor
}