package com.example.feature_dashboard.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core_common.Results
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.Command
import com.example.core_common.viewmodel.ViewState
import com.example.feature_dashboard.data.repository.DashboardRepositoryImpl
import com.example.feature_dashboard.domain.CurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepositoryImpl
) : BaseViewModel<DashboardViewState, Command>() {

    fun fetchDashboardData() = viewModelScope.launch {
        viewState.value = DashboardViewState.Loading
        viewState.value = dashboardRepository.getDashboardData().let {
            when (it) {
                is Results.Error -> DashboardViewState.Error
                is Results.Success -> DashboardViewState.CurrentWeatherLoaded(it.body)
            }
        }
    }
}

sealed class DashboardViewState : ViewState {
    object Loading : DashboardViewState()
    data class CurrentWeatherLoaded(val currentWeather: CurrentWeather) : DashboardViewState()
    object Error : DashboardViewState()
}
