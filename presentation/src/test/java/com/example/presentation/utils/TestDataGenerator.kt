package com.example.presentation.utils

import com.example.domain.entity.CommentEntityModel
import com.example.domain.entity.PostEntityModel
import com.example.presentation.model.CommentUiModel
import com.example.presentation.model.PostUiModel

class TestDataGenerator {
    companion object {
        fun generatePosts(): List<PostEntityModel> {
            val item1 = PostEntityModel(1, 1, "title 1", "test body 1")
            val item2 = PostEntityModel(1, 2, "title 2", "test body 2")
            val item3 = PostEntityModel(1, 3, "title 3", "test body 3")
            return listOf(item1, item2, item3)
        }

        fun generatePostComments(): List<CommentEntityModel> {
            val item1 = CommentEntityModel(1,1, "test name 1", "test mail 1", "test body 1")
            val item2 = CommentEntityModel(1,2, "test name 2", "test mail 2", "test body 2")
            val item3 = CommentEntityModel(1,3, "test name 3", "test mail 3", "test body 3")
            return listOf(item1, item2, item3)
        }

        fun generatePostUiModel() = PostUiModel(
            userId = 1,
            id = 1,
            title = "Test title",
            body = "Test body"
        )
    }
}