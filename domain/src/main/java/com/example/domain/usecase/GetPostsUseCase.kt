package com.example.domain.usecase

import com.example.common.extensions.Resource
import com.example.domain.entity.PostEntityModel
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<PostEntityModel>, Nothing>() {
    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PostEntityModel>>> {
        return repository.getPosts().flowOn(dispatcher)
    }
}