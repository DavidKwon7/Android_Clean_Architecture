package com.example.domain.usecase.core

import com.example.common.extensions.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.annotation.Nullable

abstract class BaseUseCase<Model, Params> {
    abstract suspend fun buildRequest(@Nullable params: Params?): Flow<Resource<Model>>

    suspend fun execute(@Nullable params: Params?): Flow<Resource<Model>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}