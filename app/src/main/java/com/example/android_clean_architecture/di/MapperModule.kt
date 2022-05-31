package com.example.android_clean_architecture.di

import com.example.common.Mapper
import com.example.data.mapper.CommentDataDomainMapper
import com.example.data.mapper.PostDataDomainMapper
import com.example.data.model.CommentDataModel
import com.example.data.model.PostDataModel
import com.example.domain.entity.CommentEntityModel
import com.example.domain.entity.PostEntityModel
import com.example.remote.mapper.CommentNetworkDataMapper
import com.example.remote.mapper.PostNetworkDataMapper
import com.example.remote.model.CommentNetworkModel
import com.example.remote.model.PostNetworkModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    // remote
    @Binds
    abstract fun bindPostNetworkDataMapper(mapper: PostNetworkDataMapper): Mapper<PostNetworkModel, PostDataModel>
    @Binds
    abstract fun bindCommentNetworkDataMapper(mapper: CommentNetworkDataMapper): Mapper<CommentNetworkModel, CommentDataModel>

   // data
    @Binds
    abstract fun bindPostDataMapper(mapper: PostDataDomainMapper): Mapper<PostDataModel, PostEntityModel>
    @Binds
    abstract fun bindCommentDataMapper(mapper: CommentDataDomainMapper): Mapper<CommentDataModel, CommentEntityModel>

}