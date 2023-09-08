package com.example.bestpractices.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core_common.viewmodel.BaseIntent
import com.example.core_common.viewmodel.BaseViewModel
import com.example.core_common.viewmodel.Command
import com.example.core_common.viewmodel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<ViewState, BaseIntent, SplashCommand>() {

    companion object {
        const val DUMMY_SPLASH_TIME_DELAY = 3000L
    }

    init {
        viewModelScope.launch {
            delay(DUMMY_SPLASH_TIME_DELAY)
            command.value = SplashCommand.NavigateToDashboard
        }
    }
}

sealed class SplashCommand : Command {
    object NavigateToDashboard : SplashCommand()
}
