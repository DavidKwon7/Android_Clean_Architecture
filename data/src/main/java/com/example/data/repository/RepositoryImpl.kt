package com.example.data.repository

import com.example.common.Mapper
import com.example.common.extensions.Resource
import com.example.data.mapper.CommentDataDomainMapper
import com.example.data.model.CommentDataModel
import com.example.data.model.PostDataModel
import com.example.domain.entity.CommentEntityModel
import com.example.domain.entity.PostEntityModel
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val postMapper: Mapper<PostDataModel, PostEntityModel>,
    private val commentMapper: Mapper<CommentDataModel, CommentEntityModel>
) : Repository {
    override suspend fun getPosts(): Flow<Resource<List<PostEntityModel>>> {
        return flow {
            try {
                val data = remoteDataSource.getPosts()
                emit(Resource.Success(postMapper.fromList(data)))
            } catch (ex: Exception) {

            }
        }
    }

    override suspend fun getPostComments(postId: Int): Flow<Resource<List<CommentEntityModel>>> {
        return flow {
            try {
                val data = remoteDataSource.getPostComments(postId)
                emit(Resource.Success(commentMapper.fromList(data)))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
        }
    }
}