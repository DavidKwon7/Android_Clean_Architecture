package com.example.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentNetworkModel(
    @SerializedName("postId")
    val postId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("body")
    val body: String?
) : Parcelable
