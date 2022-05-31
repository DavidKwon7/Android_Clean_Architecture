package com.example.android_clean_architecture.di

import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}