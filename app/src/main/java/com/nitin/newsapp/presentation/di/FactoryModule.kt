package com.nitin.newsapp.presentation.di

import android.app.Application
import com.nitin.newsapp.domain.usecase.*
import com.nitin.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(application: Application,
                                    getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
                                    getSearchNewsUseCaseFactory: GetSearchNewsUseCase,
                                    saveNewsUseCase: SaveNewsUseCase,
                                    getSavedNewsUseCase: GetSavedNewsUseCase,
    deleteSavedNewsUseCase: DeleteSavedNewsUseCase)
    : NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlineUseCase,getSearchNewsUseCaseFactory,saveNewsUseCase,getSavedNewsUseCase,deleteSavedNewsUseCase)
    }
}