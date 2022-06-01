package com.example.presentation.vm

import com.example.common.Mapper
import com.example.domain.entity.CommentEntityModel
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.model.CommentUiModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val commentMapper: Mapper<CommentEntityModel, CommentUiModel>
)  {
}