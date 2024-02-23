package com.nitin.newsapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.*
import com.nitin.newsapp.data.model.APIResponse
import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.data.util.Resource
import com.nitin.newsapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app : Application,
    val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    val getSearchNewsHeadlineUseCase: GetSearchNewsUseCase,
    val saveNewsUseCase: SaveNewsUseCase,
    val getSavedNewsUseCase: GetSavedNewsUseCase,
    val deleteSavedNewsUseCase: DeleteSavedNewsUseCase): AndroidViewModel(app) {


    val newsHeadline : MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    val searchHeadline : MutableLiveData<Resource<APIResponse>> = MutableLiveData()



    fun getNewsHeadline(country : String,page:Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadline.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = getNewsHeadlineUseCase.execute(country, page)
                newsHeadline.postValue(apiResult)
            }else{
                newsHeadline.postValue(Resource.Error("Internet Not Available"))
            }
        }catch (exception: Exception){
            newsHeadline.postValue(Resource.Error(exception.message.toString()))
        }

    }


    fun getSearchNewsHeadline(country : String,searchQuery: String,page:Int) = viewModelScope.launch(Dispatchers.IO) {
        searchHeadline.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = getSearchNewsHeadlineUseCase.execute(country, searchQuery ,page)
                searchHeadline.postValue(apiResult)
            }else{
                searchHeadline.postValue(Resource.Error("Internet Not Available"))
            }
        }catch (exception: Exception){
            searchHeadline.postValue(Resource.Error(exception.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false

    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    fun getSavedArticles() = getSavedNewsUseCase.execute().asLiveData()

    fun deleteSavedNews(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }


}