package com.example.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.extensions.Resource
import com.example.domain.entity.CommentEntityModel
import com.example.domain.usecase.GetPostCommentsUseCase
import com.example.presentation.contract.DetailContract
import com.example.presentation.model.CommentUiModel
import com.example.presentation.model.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostCommentsUseCase: GetPostCommentsUseCase,
    private val commentMapper: Mapper<CommentEntityModel, CommentUiModel>
): BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun createInitialState(): DetailContract.State {
        return DetailContract.State(
            commentState = DetailContract.CommnetState.Idle
        )
    }

    override fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.OnFetchPostComments -> {
                val post = event.post
                fetchPostComments(post = post)
            }
        }
    }

    private fun fetchPostComments(post: PostUiModel?) {
        viewModelScope.launch {
            getPostCommentsUseCase.execute(post?.id)
                .onStart { emit(Resource.Loading) }
                .collect{
                    when(it) {
                        is Resource.Loading -> {
                            setState { copy(commentState = DetailContract.CommnetState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(commentState = DetailContract.CommnetState.Idle) }
                        }
                        is Resource.Success -> {
                            val comments = it.data
                            setState { copy(commentState = DetailContract.CommnetState.Success(commentMapper.fromList(list = comments))) }
                        }
                        is Resource.Error -> {
                            setEffect { DetailContract.Effect.showError(message = it.exception.message) }
                        }
                    }
                }
        }
    }

}