package com.fired.network.di

import com.fired.network.NetworkMonitor
import com.fired.network.NetworkMonitorImpl
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
interface NetworkMonitorModule {
    @Binds
    fun bindNetworkMonitor(networkMonitor: NetworkMonitorImpl): NetworkMonitor
}