package com.nitin.newsapp.presentation.di

import com.nitin.newsapp.data.repositiory.NewsRepositoryImpl
import com.nitin.newsapp.data.repositiory.dataSource.NewsLocalDataSource
import com.nitin.newsapp.data.repositiory.dataSource.NewsRemoteDataSource
import com.nitin.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(newsRemoteDataSource: NewsRemoteDataSource,newsLocalDataSource: NewsLocalDataSource) : NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }
}