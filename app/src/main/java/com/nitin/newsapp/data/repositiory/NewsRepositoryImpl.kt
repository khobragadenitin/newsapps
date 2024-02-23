package com.nitin.newsapp.data.repositiory

import com.nitin.newsapp.data.model.APIResponse
import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.data.repositiory.dataSource.NewsLocalDataSource
import com.nitin.newsapp.data.repositiory.dataSource.NewsRemoteDataSource
import com.nitin.newsapp.data.util.Resource
import com.nitin.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource,
private val newsLocalDataSource: NewsLocalDataSource) : NewsRepository {


    override suspend fun getNewsHeadline(country : String, page : Int): Resource<APIResponse> {
        return getResponseToResource(newsRemoteDataSource.getTopHeadline(country, page))
    }

    override suspend fun getSearchNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return getResponseToResource(newsRemoteDataSource.getSearchedTopHeadline(country, searchQuery,page))
    }


    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveNewsArticle(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedNews()
    }

    private fun getResponseToResource(response: Response<APIResponse>) : Resource<APIResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())

    }
}