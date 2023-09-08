package com.example.feature_dashboard.repository

import com.example.core_common.Results
import com.example.core_common.dto.ErrorCode
import com.example.core_common.dto.NetworkError
import com.example.feature_dashboard.data.api.DashboardAPI
import com.example.feature_dashboard.data.dto.Main
import com.example.feature_dashboard.data.dto.WeatherData
import com.example.feature_dashboard.data.repository.DashboardRepository
import com.example.feature_dashboard.data.repository.DashboardRepositoryImpl
import com.example.feature_dashboard.domain.CurrentWeather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardRepositoryTest {

    private val networkError: NetworkError =
        NetworkError(ErrorCode.UNKNOWN_ERROR, "Something went wrong")
    private lateinit var repository: DashboardRepository

    private val mockMain = mockk<Main>() {
        every { humidity } returns 1
        every { temp } returns 2.3
        every { tempMax } returns 2.0
        every { tempMin } returns 2.3
        every { humidity } returns 2
    }
    private val mockWeatherData = mockk<WeatherData> {
        every { name } returns "Edinburgh"
        every { main } returns mockMain
        every { visibility } returns 2
    }
    private val mockCurrentWeather = mockk<CurrentWeather>() {
        every { location } returns mockWeatherData.name
        every { temp } returns mockMain.temp
        every { maxTemp } returns mockMain.tempMax
        every { minTemp } returns mockMain.tempMin
        every { humidity } returns mockMain.humidity
        every { visibility } returns mockWeatherData.visibility
    }
    private val mockDashboardAPI = mockk<DashboardAPI>()

    private val testDispatchers: CoroutineDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        repository = DashboardRepositoryImpl(mockDashboardAPI, testDispatchers)
    }

    @Test
    fun `when get weather data is called with success, Current weather data is expected`() =
        runTest {
            coEvery {
                mockDashboardAPI.getWeatherData(any(), any(), any())
            } returns Results.Success(
                mockWeatherData
            )
            val dashboardData: Results<CurrentWeather> = repository.getDashboardData()
            Assertions.assertThat(dashboardData).isEqualTo(Results.Success(mockCurrentWeather))
        }

    @Test
    fun `when get weather data is called with Error, result with Error is expected`() =
        runTest {
            coEvery {
                mockDashboardAPI.getWeatherData(any(), any(), any())
            } returns Results.Error(
                networkError
            )
            val dashboardData: Results<CurrentWeather> = repository.getDashboardData()
            Assertions.assertThat(dashboardData).isEqualTo(Results.Error(networkError))
        }
}