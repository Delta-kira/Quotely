package com.example.quotely.Api

import com.example.quotely.Models.QuotesListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface QuotelyApi {
    @GET("/v3/b/6585356f1f5677401f11c11c?meta=false")
    suspend fun getQuotes(@Header("X-JSON-Path") category: String): Response<List<QuotesListItem>>

    @GET("/v3/b/6585356f1f5677401f11c11c?meta=false")
    @Headers("X-JSON-Path:quotes..category")
    suspend fun getCategory(): Response<List<String>>
}