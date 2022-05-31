package com.example.data.repository

import com.example.data.model.CommentDataModel
import com.example.data.model.PostDataModel

interface RemoteDataSource {

    suspend fun getPosts(): List<PostDataModel>

    suspend fun getPostComments(postId: Int): List<CommentDataModel>
}