package com.example.feature_dashboard.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core_common.Results
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.Command
import com.example.core_common.viewmodel.ViewState
import com.example.feature_dashboard.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : BaseViewModel<DashboardViewState, DashboardCommand>() {

    init {
        viewModelScope.launch {
            viewState.value = DashboardViewState.Loading
            fetchDashboardData()
        }
    }

    private suspend fun fetchDashboardData() {
        viewState.value = dashboardRepository.getDashboardData().let {
            when (it) {
                is Results.Error -> DashboardViewState.Error
                is Results.Success -> DashboardViewState.Weather
            }
        }
    }
}

sealed class DashboardCommand : Command
sealed class DashboardViewState : ViewState {
    object Loading : DashboardViewState()
    object Weather : DashboardViewState()
    object Error : DashboardViewState()
}
