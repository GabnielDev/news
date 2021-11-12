package com.example.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapi.data.ArticlesItem
import com.example.newsapi.network.ApiClient
import com.example.newsapi.utils.Constants.API_KEY
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    fun getHeadlines(page: Int): LiveData<ArrayList<ArticlesItem?>> {
        val headlines = MutableLiveData<ArrayList<ArticlesItem?>>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getHeadlines("id", API_KEY, page)
                if (data.isSuccessful) {
                    headlines.value = data.body()?.articles
                } else {
                    status.value = data.code()
                }
                loading.value = false
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> message.value = t.message.toString()
                    is HttpException -> message.value = t.message().toString()
                    else -> message.value = "Unknow Error"
                }
                loading.value = false
            }
        }
        return headlines
    }

    fun getCategory(page: Int, category: String): LiveData<ArrayList<ArticlesItem?>> {
        val kategori = MutableLiveData<ArrayList<ArticlesItem?>>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getCategory("id", category, API_KEY, page)
                if (data.isSuccessful) {
                    kategori.value = data.body()?.articles
                } else {
                    status.value = data.code()
                }
                loading.value = false
            } catch (t: Throwable) {
                when(t) {
                    is IOException -> message.value = t.message.toString()
                    is HttpException -> message.value = t.message().toString()
                    else -> message.value = "Unknow Error"
                }
                loading.value = false
            }
        }
        return kategori
    }

    fun getLoading(): LiveData<Boolean> = loading
    fun getStatus(): LiveData<Int> = status
    fun getMessage(): LiveData<String> = message
}