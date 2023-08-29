package com.example.core_network.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitApiFactory @Inject constructor() {

    fun create(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        objectMapper: ObjectMapper,
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(CallAdapterFactory())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .baseUrl(baseUrl)
            .build()
}
