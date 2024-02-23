package com.nitin.newsapp.data.repositiory.dataSource

import com.nitin.newsapp.data.model.APIResponse
import retrofit2.Response


interface NewsRemoteDataSource {

    suspend fun getTopHeadline(country : String, page : Int) : Response<APIResponse>


    suspend fun getSearchedTopHeadline(country : String,searchQuery : String, page : Int) : Response<APIResponse>
}