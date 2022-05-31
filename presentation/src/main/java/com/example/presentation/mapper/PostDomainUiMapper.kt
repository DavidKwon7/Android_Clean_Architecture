package com.example.presentation.mapper

import com.example.common.Mapper
import com.example.domain.entity.PostEntityModel
import com.example.presentation.model.PostUiModel
import javax.inject.Inject

class PostDomainUiMapper @Inject constructor()
    :Mapper<PostUiModel, PostEntityModel>{
    override fun from(i: PostUiModel?): PostEntityModel {
        return PostEntityModel(
            userId = i?.userId,
            id = i?.id,
            title = i?.title,
            body = i?.body
        )
    }

    override fun to(o: PostEntityModel?): PostUiModel {
        return PostUiModel(
            userId = o?.userId,
            id = o?.id,
            title = o?.title,
            body = o?.body
        )
    }
}