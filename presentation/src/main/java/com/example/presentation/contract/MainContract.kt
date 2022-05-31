package com.example.presentation.contract

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.presentation.model.PostUiModel


class MainContract {

    sealed class Event : UiEvent {
        object OnFetchPosts: Event()
        data class OnPostItemClicked(val post: PostUiModel) : Event()
    }

    data class State(
        val postsState: PostsState,
        val selectedPost: PostUiModel? = null
    )

    sealed class PostsState {
        object Idle: PostsState()
        object Loading: PostsState()
        data class Success(val posts: List<PostUiModel>) : PostsState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}