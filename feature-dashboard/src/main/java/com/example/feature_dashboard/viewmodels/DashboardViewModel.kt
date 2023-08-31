package com.example.feature_dashboard.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.Command
import com.example.core_common.viewmodel.ViewState
import com.example.feature_dashboard.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : BaseViewModel<DashboardViewState, DashboardCommand>() {

    init {
        viewModelScope.launch {
            viewState.value = DashboardViewState.Loading
            delay(2300)
            viewState.value = DashboardViewState.Loaded
        }
    }
}

sealed class DashboardCommand : Command
sealed class DashboardViewState : ViewState {
    object Loading : DashboardViewState()
    object Loaded : DashboardViewState()
}
