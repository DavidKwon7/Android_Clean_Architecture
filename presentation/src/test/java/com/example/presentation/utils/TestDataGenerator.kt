package com.example.presentation.utils

import com.example.presentation.model.CommentUiModel
import com.example.presentation.model.PostUiModel

class TestDataGenerator {
    companion object {
        fun generatePosts(): List<PostUiModel> {
            val item1 = PostUiModel(1, 1, "title 1", "test body 1")
            val item2 = PostUiModel(1, 2, "title 2", "test body 2")
            val item3 = PostUiModel(1, 3, "title 3", "test body 3")
            return listOf(item1, item2, item3)
        }

        fun generatePostComments(): List<CommentUiModel> {
            val item1 = CommentUiModel(1,1, "test name 1", "test mail 1", "test body 1")
            val item2 = CommentUiModel(1,2, "test name 2", "test mail 2", "test body 2")
            val item3 = CommentUiModel(1,3, "test name 3", "test mail 3", "test body 3")
            return listOf(item1, item2, item3)
        }
    }
}