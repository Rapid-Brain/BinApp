package com.fired.binapp.di

import com.fired.binapp.util.ApiConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = ApiConstant.BaseUrl
}