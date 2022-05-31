package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.PostDataModel
import com.example.remote.model.PostNetworkModel
import javax.inject.Inject

class PostNetworkDataMapper @Inject constructor()
    :Mapper<PostNetworkModel, PostDataModel>{
    override fun from(i: PostNetworkModel?): PostDataModel {
        return PostDataModel(
            userId = i?.userId,
            id = i?.id,
            title = i?.title,
            body = i?.body
        )
    }

    override fun to(o: PostDataModel?): PostNetworkModel {
        return PostNetworkModel(
            userId = o?.userId,
            id = o?.id,
            title = o?.title,
            body = o?.body
        )
    }
}