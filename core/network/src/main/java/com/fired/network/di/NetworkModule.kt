package com.fired.network.di

import com.fired.network.NetworkMonitorImpl
import com.fired.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author yaya (@yahyalmh)
 * @since 28th October 2022
 */
@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindNetworkMonitor(
        networkMonitor: NetworkMonitorImpl
    ): NetworkMonitor
}