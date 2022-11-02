package com.fired.binapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object{
        const val baseUrlName = "BaseUrl"
        const val BaseUrl = "127.0.0.1"
    }

    @Named(baseUrlName)
    fun provideBaseUrl() = BaseUrl
}