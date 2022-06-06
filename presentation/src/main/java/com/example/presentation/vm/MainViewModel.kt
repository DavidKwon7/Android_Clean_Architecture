package com.example.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.common.Mapper
import com.example.domain.entity.CommentEntityModel
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.model.CommentUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val commentMapper: Mapper<CommentEntityModel, CommentUiModel>
): ViewModel() {
}