package com.example.dashboard.domain

import com.example.dashboard.data.dto.WeatherData

data class CurrentWeather(
    val location: String,
    val humidity: Int,
    val visibility: Int,
    val temp: Double,
    val maxTemp: Double,
    val minTemp: Double,
)

fun WeatherData.mapToCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        name,
        main.humidity,
        visibility,
        main.temp,
        main.tempMax,
        main.tempMin
    )
}