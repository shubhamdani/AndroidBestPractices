package com.example.core_common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : ViewState, C : Command> : ViewModel() {
    val command = MutableLiveData<C>()
    val viewState = MutableLiveData<V>()
}

interface Command
interface ViewState