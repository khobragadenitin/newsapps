package com.nitin.newsapp.data.repositiory.dataSourceImpl

import com.nitin.newsapp.data.db.ArticleDao
import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.data.repositiory.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSouceImpl(val articleDao: ArticleDao) : NewsLocalDataSource {
    override suspend fun saveNewsArticle(article: Article) {
        articleDao.insert(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return articleDao.getSavedNews()
    }

    override suspend fun deleteArticle(article: Article) {
        return articleDao.deleteArticle(article)
    }
}