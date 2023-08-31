package com.example.feature_dashboard.data.repository

import com.example.feature_dashboard.data.api.DashboardAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
    private val dashboardAPI: DashboardAPI
) {

}
