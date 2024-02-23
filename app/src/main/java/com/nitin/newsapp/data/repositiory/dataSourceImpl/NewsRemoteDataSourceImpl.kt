package com.nitin.newsapp.data.repositiory.dataSourceImpl

import com.nitin.newsapp.data.api.NewsAPIService
import com.nitin.newsapp.data.model.APIResponse
import com.nitin.newsapp.data.repositiory.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService) : NewsRemoteDataSource {

    override suspend fun getTopHeadline(country : String, page : Int): Response<APIResponse> {
        return newsAPIService.getTopHeadline(country,page)
    }

    override suspend fun getSearchedTopHeadline(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchTopHeadline(country,page,searchQuery)
    }
}