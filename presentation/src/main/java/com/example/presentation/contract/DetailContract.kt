package com.example.presentation.contract

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.presentation.model.CommentUiModel
import com.example.presentation.model.PostUiModel

class DetailContract {

    sealed class Event: UiEvent {
        data class OnFetchPostComments(val post: PostUiModel?): Event()
    }

    data class State (
        val commentState: CommnetState
    ) : UiState

    sealed class CommnetState {
        object Idle: CommnetState()
        object Loading: CommnetState()
        data class Success(val comments: List<CommentUiModel>) : CommnetState()
    }

    sealed class Effect: UiEffect {
        data class showError(val message: String?): Effect()
    }
}