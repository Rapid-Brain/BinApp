package com.fired.network.di

import com.fired.network.BuildConfig
import com.fired.network.NetworkMonitorImpl
import com.fired.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author yaya (@yahyalmh)
 * @since 28th October 2022
 */
@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
//            val logging = HttpLoggingInterceptor()
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpClient: OkHttpClient,
                         @Named("BaseUrl") baseUrl:String): Retrofit =
        Retrofit
            .Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//    @Provides
//    @Singleton
//    fun providePeaKApi(retrofit: Retrofit): PeakApi = retrofit.create(PeakApi::class.java)

    @Binds
    fun bindNetworkMonitor(
        networkMonitor: NetworkMonitorImpl
    ): NetworkMonitor
}