package com.nitin.newsapp.domain.usecase

import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {


    suspend fun execute(article: Article) = newsRepository.saveNews(article)

}