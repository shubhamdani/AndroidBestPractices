package com.example.feature_dashboard.data.di

import com.example.network.retrofit.RetrofitApiFactory
import com.example.feature_dashboard.data.api.DashboardAPI
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@InstallIn(SingletonComponent::class)
@Module
class DashboardApiModule {

    private val weatherApiBaseUrl: String = "https://api.openweathermap.org"

    @Provides
    fun provideDashboardAPI(
        retrofitApiFactory: RetrofitApiFactory,
        okHttpClient: OkHttpClient,
        objectMapper: ObjectMapper
    ): DashboardAPI {
        return retrofitApiFactory.create(weatherApiBaseUrl, okHttpClient, objectMapper)
            .create(DashboardAPI::class.java)
    }
}