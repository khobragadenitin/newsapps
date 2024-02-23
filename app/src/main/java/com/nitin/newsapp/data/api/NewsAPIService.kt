package com.nitin.newsapp.data.api


import com.nitin.newsapp.BuildConfig
import com.nitin.newsapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadline(
        @Query("country") country : String,
        @Query("page") page:Int,
        @Query("apiKey") apiKey : String =BuildConfig.API_KEY

    ): Response<APIResponse>


    @GET("/v2/top-headlines")
    suspend fun getSearchTopHeadline(
        @Query("country") country : String,
        @Query("page") page:Int,
        @Query("q") searchQuery : String,
        @Query("apiKey") apiKey : String =BuildConfig.API_KEY

    ): Response<APIResponse>
}