package com.example.feature_dashboard.data.repository

import com.example.feature_dashboard.data.api.DashboardAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
    private val dashboardAPI: DashboardAPI
) {
    suspend fun getDashboardData() = withContext(Dispatchers.IO) {
        dashboardAPI.getWeatherData(
            "33.44",
            "-94.04",
            "d4b92dee258bf3ca907db33745a525e4",
        )
    }
}
