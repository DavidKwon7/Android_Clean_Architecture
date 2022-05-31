package com.example.domain.usecase

import com.example.common.extensions.Resource
import com.example.domain.entity.CommentEntityModel
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<CommentEntityModel>, Int>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<List<CommentEntityModel>>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("PostId can not be null")))
            }.flowOn(dispatcher)
        }
        return repository.getPostComments(postId = params).flowOn(dispatcher)
    }
}