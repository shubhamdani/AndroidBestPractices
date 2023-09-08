package com.example.feature_dashboard.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core_common.Results
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.BaseIntent
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
) : BaseViewModel<DashboardViewState, DashboardIntent, Command>() {

    private fun fetchDashboardData() = viewModelScope.launch {
        viewState.value = DashboardViewState.Loading
        viewState.value = dashboardRepository.getDashboardData().let {
            when (it) {
                is Results.Error -> DashboardViewState.Error
                is Results.Success -> DashboardViewState.DashBoardData(it.body)
            }
        }
    }

    override fun performActions(intent: DashboardIntent) {
        when (intent) {
            DashboardIntent.FetchDashboardData -> fetchDashboardData()
        }
    }
}

sealed class DashboardIntent : BaseIntent {
    object FetchDashboardData : DashboardIntent()
}

sealed class DashboardViewState : ViewState {
    object Loading : DashboardViewState()
    data class DashBoardData(val currentWeather: CurrentWeather) : DashboardViewState()
    object Error : DashboardViewState()
}
