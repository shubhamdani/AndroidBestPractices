package com.example.feature_dashboard.data.repository

import com.example.common.Results
import com.example.common.di.DispatchersIO
import com.example.feature_dashboard.data.api.DashboardAPI
import com.example.feature_dashboard.data.dto.WeatherData
import com.example.feature_dashboard.domain.CurrentWeather
import com.example.feature_dashboard.domain.mapToCurrentWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface DashboardRepository {
    suspend fun getDashboardData(): Results<CurrentWeather>
}

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    private val dashboardAPI: DashboardAPI,
    @DispatchersIO private val dispatchers: CoroutineDispatcher
) : DashboardRepository {

    companion object {
        private const val latitude: String = "33.44"
        private const val longitude: String = "-94.04"
        private const val apiKey: String = "d4b92dee258bf3ca907db33745a525e4"
    }

    override suspend fun getDashboardData(): Results<CurrentWeather> {
        val results = withContext(dispatchers) {
            dashboardAPI.getWeatherData(
                latitude, longitude,
                apiKey,
            )
        }
        return when (results) {
            is Results.Error -> results
            is Results.Success -> Results.Success(results.body.mapToCurrentWeather())
        }
    }
}
