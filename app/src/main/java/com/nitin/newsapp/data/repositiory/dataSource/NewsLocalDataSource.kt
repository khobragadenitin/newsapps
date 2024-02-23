package com.nitin.newsapp.data.repositiory.dataSource

import com.nitin.newsapp.data.model.APIResponse
import com.nitin.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsLocalDataSource {

    suspend fun saveNewsArticle(article: Article)

    fun getSavedNews() : Flow<List<Article>>

    suspend fun deleteArticle(article: Article)
}