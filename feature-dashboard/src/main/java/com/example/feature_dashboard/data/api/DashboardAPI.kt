package com.example.feature_dashboard.data.api

import com.example.feature_dashboard.data.dto.WeatherData
import retrofit2.http.POST
import retrofit2.http.Query
import com.example.common.Results

interface DashboardAPI {

    @POST("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
    ): Results<WeatherData>
}
