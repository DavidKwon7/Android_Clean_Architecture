package com.example.remote.source

import com.example.common.Mapper
import com.example.data.model.CommentDataModel
import com.example.data.model.PostDataModel
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.model.CommentNetworkModel
import com.example.remote.model.PostNetworkModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val postMapper : Mapper<PostNetworkModel, PostDataModel>,
    private val commentMapper: Mapper<CommentNetworkModel, CommentDataModel>
) : RemoteDataSource {
    override suspend fun getPosts(): List<PostDataModel> {
        val networkData = apiService.getPosts()
        return postMapper.fromList(networkData)
    }

    override suspend fun getPostComments(postId: Int): List<CommentDataModel> {
        val networkData = apiService.getPostComments(postId)
        return commentMapper.fromList(networkData)
    }
}