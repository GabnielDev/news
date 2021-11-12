package com.example.newsapi.network

import com.example.newsapi.data.Headlines
import com.example.newsapi.utils.Constants.API_KEY
import com.example.newsapi.utils.Constants.HEADLINES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(HEADLINES)
    suspend fun getHeadlines(
        @Query("country") country: String = "id",
        @Query("apikey") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Response<Headlines>

    @GET(HEADLINES)
    suspend fun getCategory(
        @Query("country") country: String = "id",
        @Query("category") category: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Response<Headlines>

}