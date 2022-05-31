package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.CommentDataModel
import com.example.remote.model.CommentNetworkModel
import javax.inject.Inject

class CommentNetworkDataMapper @Inject constructor()
    :Mapper<CommentNetworkModel, CommentDataModel>{
    override fun from(i: CommentNetworkModel?): CommentDataModel {
        return CommentDataModel(
            postId = i?.postId,
            id = i?.id,
            name = i?.name,
            email = i?.email,
            body = i?.body
        )
    }

    override fun to(o: CommentDataModel?): CommentNetworkModel {
        return CommentNetworkModel(
            postId = o?.postId,
            id = o?.id,
            name = o?.name,
            email = o?.email,
            body = o?.body
        )
    }
}