package com.example.domain.repository

import com.example.common.extensions.Resource
import com.example.domain.entity.CommentEntityModel
import com.example.domain.entity.PostEntityModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPosts(): Flow<Resource<List<PostEntityModel>>>

    suspend fun getPostComments(postId: Int): Flow<Resource<List<CommentEntityModel>>>
}