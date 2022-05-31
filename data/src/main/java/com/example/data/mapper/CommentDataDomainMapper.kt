package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.CommentDataModel
import com.example.domain.entity.CommentEntityModel
import javax.inject.Inject

class CommentDataDomainMapper @Inject constructor(
) : Mapper<CommentDataModel, CommentEntityModel> {
    override fun from(i: CommentDataModel?): CommentEntityModel {
        return CommentEntityModel(
            postId = i?.postId,
            id = i?.id,
            name = i?.name,
            email = i?.email,
            body = i?.body
        )
    }

    override fun to(o: CommentEntityModel?): CommentDataModel {
        return CommentDataModel(
            postId = o?.postId,
            id = o?.id,
            name = o?.name,
            email = o?.email,
            body = o?.body
        )
    }
}