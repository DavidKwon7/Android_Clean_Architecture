package com.example.presentation.mapper

import com.example.common.Mapper
import com.example.domain.entity.CommentEntityModel
import com.example.presentation.model.CommentUiModel
import javax.inject.Inject

class CommentDomainUiMapper @Inject constructor(): Mapper<CommentUiModel, CommentEntityModel> {
    override fun from(i: CommentUiModel?): CommentEntityModel {
        return CommentEntityModel(
            postId = i?.postId,
            id = i?.id,
            name = i?.name,
            email = i?.email,
            body = i?.body
        )
    }

    override fun to(o: CommentEntityModel?): CommentUiModel {
        return CommentUiModel(
            postId = o?.postId,
            id = o?.id,
            name = o?.name,
            email = o?.email,
            body = o?.body
        )
    }
}