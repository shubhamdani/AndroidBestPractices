package com.example.core_common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : Command> : ViewModel() {
    val command = MutableLiveData<V>()
}

interface Command