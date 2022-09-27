package com.marazmone.mapsclustertest.presentation.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.properties.Delegates
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : BaseViewState, ACTION : BaseViewAction, EFFECT : BaseViewEffect> :
    ViewModel() {

    abstract fun setInitialState(): STATE

    private val initialState: STATE by lazy { setInitialState() }

    private val _state: MutableState<STATE> = mutableStateOf(initialState)
    val state: State<STATE> = _state

    private val _effects = Channel<EFFECT>()
    val effects = _effects.receiveAsFlow()

    protected var currentState by Delegates.observable(initialState) { _, _, new ->
        _state.value = new
    }

    protected fun sendEffect(builder: () -> EFFECT) {
        val effectValue = builder()
        viewModelScope.launch { _effects.send(effectValue) }
    }

    protected fun sendAction(builder: () -> ACTION) {
        val actionValue = builder()
        currentState = onReduceState(actionValue)
    }

    protected abstract fun onReduceState(action: ACTION): STATE
}

interface BaseViewState

interface BaseViewAction

interface BaseViewEffect
