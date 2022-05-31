package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.PostDataModel
import com.example.domain.entity.PostEntityModel
import javax.inject.Inject

class PostDataDomainMapper @Inject constructor(
) : Mapper<PostDataModel, PostEntityModel> {
    override fun from(i: PostDataModel?): PostEntityModel {
        return PostEntityModel(
            userId = i?.userId,
            id = i?.id,
            title = i?.title,
            body = i?.body
        )
    }

    override fun to(o: PostEntityModel?): PostDataModel {
        return PostDataModel(
            userId = o?.userId,
            id = o?.id,
            title = o?.title,
            body = o?.body
        )
    }
}