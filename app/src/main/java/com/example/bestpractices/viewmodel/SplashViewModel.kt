package com.example.bestpractices.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.Command
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<SplashViewModel.SplashCommands>() {

    companion object {
        const val DUMMY_SPLASH_TIME_DELAY = 3000L
    }

    init {
        viewModelScope.launch {
            delay(DUMMY_SPLASH_TIME_DELAY)
            command.value = SplashCommands.NavigateToDashboard
        }
    }

    sealed class SplashCommands : Command {
        object NavigateToDashboard : SplashCommands()
    }
}
