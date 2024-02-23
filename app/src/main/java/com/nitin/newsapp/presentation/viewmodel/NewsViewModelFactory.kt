package com.nitin.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nitin.newsapp.domain.usecase.*

class NewsViewModelFactory(private val app : Application,
                           val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
                           val getSearchNewsUseCase: GetSearchNewsUseCase,
                           val saveNewsUseCase: SaveNewsUseCase,
                           val getSavedNewsUseCase: GetSavedNewsUseCase,
                           val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlineUseCase,getSearchNewsUseCase,saveNewsUseCase,getSavedNewsUseCase,deleteSavedNewsUseCase ) as T
    }
}