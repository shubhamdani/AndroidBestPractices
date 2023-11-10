package com.example.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<V : ViewState, I : BaseIntent, C: Command> : ViewModel() {

    val intent = Channel<I>(Channel.UNLIMITED)
    protected val command = MutableLiveData<C>()
    val observableCommand: LiveData<C>
        get() = command
    protected val viewState = MutableLiveData<V>()
    val observableViewState: LiveData<V>
        get() = viewState

    init { observeIntents() }

    private fun observeIntents() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect { performActions(it) }
        }
    }

    protected open fun performActions(intent: I) {}
}

interface BaseIntent
interface ViewState
interface Command
