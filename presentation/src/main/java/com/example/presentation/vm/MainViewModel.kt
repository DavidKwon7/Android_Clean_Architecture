package com.example.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.extensions.Resource
import com.example.domain.entity.CommentEntityModel
import com.example.domain.entity.PostEntityModel
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.contract.MainContract
import com.example.presentation.model.CommentUiModel
import com.example.presentation.model.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase,
    private val postMapper: Mapper<PostEntityModel, PostUiModel>
): BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            postsState = MainContract.PostsState.Idle,
            selectedPost = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when(event) {
            is MainContract.Event.OnFetchPosts -> {
                fetchPosts()
            }
            is MainContract.Event.OnPostItemClicked -> {
                val item =event.post
                setSelectedPost(post = item)
            }
        }
    }


    private fun fetchPosts() {
        viewModelScope.launch {
            postsUseCase.execute(null)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when(it) {
                        is Resource.Loading -> {
                            setState { copy(postsState = MainContract.PostsState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(postsState = MainContract.PostsState.Idle) }
                        }
                        is Resource.Success -> {
                            val posts = postMapper.fromList(it.data)
                            setState { copy(postsState = MainContract.PostsState.Success(posts = posts)) }
                        }
                        is Resource.Error -> {
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }
    private fun setSelectedPost(post: PostUiModel?) {
        setState { copy(selectedPost = post) }
    }
}