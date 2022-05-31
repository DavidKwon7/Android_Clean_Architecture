package com.example.remote.api

import com.example.remote.model.CommentNetworkModel
import com.example.remote.model.PostNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostNetworkModel>

    @GET("posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): List<CommentNetworkModel>
}