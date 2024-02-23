package com.nitin.newsapp.presentation.di

import com.nitin.newsapp.data.api.NewsAPIService
import com.nitin.newsapp.data.db.ArticleDao
import com.nitin.newsapp.data.repositiory.dataSource.NewsLocalDataSource
import com.nitin.newsapp.data.repositiory.dataSource.NewsRemoteDataSource
import com.nitin.newsapp.data.repositiory.dataSourceImpl.NewsLocalDataSouceImpl
import com.nitin.newsapp.data.repositiory.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(apiService: NewsAPIService) : NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(dao: ArticleDao) : NewsLocalDataSource{
        return NewsLocalDataSouceImpl(dao)
    }
}