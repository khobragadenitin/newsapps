package com.nitin.newsapp.domain.usecase

import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute() : Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }

}