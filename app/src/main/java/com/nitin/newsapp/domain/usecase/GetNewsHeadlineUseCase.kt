package com.nitin.newsapp.domain.usecase

import com.nitin.newsapp.data.model.APIResponse
import com.nitin.newsapp.data.util.Resource
import com.nitin.newsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country : String, page : Int) : Resource<APIResponse> {
        return newsRepository.getNewsHeadline(country, page)
    }

}