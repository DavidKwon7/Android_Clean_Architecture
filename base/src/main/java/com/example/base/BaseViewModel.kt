package com.example.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Event: UiEvent, State: UiState, Effect: UiEffect>
    : ViewModel() {
}