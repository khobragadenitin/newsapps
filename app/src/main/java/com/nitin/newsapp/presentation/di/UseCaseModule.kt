package com.nitin.newsapp.presentation.di

import com.nitin.newsapp.domain.repository.NewsRepository
import com.nitin.newsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCase(newsRepository: NewsRepository) : GetNewsHeadlineUseCase{
        return GetNewsHeadlineUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetSearchNewsUseCase(newsRepository: NewsRepository) : GetSearchNewsUseCase{
        return GetSearchNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(newsRepository: NewsRepository) : SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSaveNewsUseCase(newsRepository: NewsRepository) : GetSavedNewsUseCase{
        return GetSavedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSaveNewsUseCase(newsRepository: NewsRepository) : DeleteSavedNewsUseCase{
        return DeleteSavedNewsUseCase(newsRepository)
    }


}