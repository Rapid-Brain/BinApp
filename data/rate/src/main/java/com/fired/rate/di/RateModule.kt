package com.fired.rate.di

import com.fired.rate.RateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */
@Module
@InstallIn(SingletonComponent::class)
class RateModule {

    @Provides
    @Singleton
    fun provideRateApi(retrofit: Retrofit): RateApi = retrofit.create(RateApi::class.java)


}