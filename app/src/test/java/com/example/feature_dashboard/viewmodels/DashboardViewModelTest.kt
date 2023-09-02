package com.example.feature_dashboard.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bestpractices.utils.MainDispatcherRule
import com.example.core_common.Results
import com.example.core_common.dto.ErrorCode
import com.example.core_common.dto.NetworkError
import com.example.feature_dashboard.data.repository.DashboardRepositoryImpl
import com.example.feature_dashboard.domain.CurrentWeather
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val instantExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule: MainDispatcherRule = MainDispatcherRule()

    private val currentWeather = mockk<CurrentWeather>()

    private val mockDashboardRepository = mockk<DashboardRepositoryImpl>() {
        coEvery { getDashboardData() } returns Results.Success(
            currentWeather
        )
    }
    private val viewStateList: MutableList<DashboardViewState> = mutableListOf()
    private val slotViewState = slot<DashboardViewState>()
    private val mockViewObserver = mockk<Observer<DashboardViewState>>() {
        every { onChanged(capture(slotViewState)) } answers {
            viewStateList.add(slotViewState.captured)
        }
    }

    private lateinit var viewModel: DashboardViewModel

    private fun setup() {
        viewModel = DashboardViewModel(mockDashboardRepository)
        viewModel.viewState.observeForever(mockViewObserver)
    }

    @Test
    fun `emit view state in a sequence when fetchDashboardData is called with Success result`() {
        setup()
        viewModel.fetchDashboardData()
        verifySequence {
            mockViewObserver.onChanged(DashboardViewState.Loading)
            mockViewObserver.onChanged(DashboardViewState.CurrentWeatherLoaded(currentWeather))
        }
    }

    @Test
    fun `emit view state in a sequence when fetchDashboardData is called with Failure`() {
        coEvery { mockDashboardRepository.getDashboardData() } returns Results.Error(
            NetworkError(ErrorCode.UNKNOWN_ERROR)
        )
        setup()
        viewModel.fetchDashboardData()
        verifySequence {
            mockViewObserver.onChanged(DashboardViewState.Loading)
            mockViewObserver.onChanged(DashboardViewState.Error)
        }
    }
}