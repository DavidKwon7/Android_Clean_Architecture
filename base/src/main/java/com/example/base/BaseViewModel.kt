package com.example.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Event: UiEvent, State: UiState, Effect: UiEffect> : ViewModel() {

    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState(): State


}