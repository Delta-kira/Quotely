package com.example.quotely.Repository

import android.util.Log
import com.example.quotely.Api.QuotelyApi
import com.example.quotely.Models.QuotesListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class QuotelyRepository @Inject constructor(private val quotelyapi: QuotelyApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _quotes = MutableStateFlow<List<QuotesListItem>>(emptyList())
    val quotes: StateFlow<List<QuotesListItem>>
        get() = _quotes

    suspend fun getCategories() {
        val result = quotelyapi.getCategory()
        if (result.isSuccessful) {
            _categories.emit(result.body()!!)
        } else {
            Log.d("Kira", "Error")
        }
    }

    suspend fun getQuotes(category: String) {
        val result = quotelyapi.getQuotes("quotes[?(@.category==\"$category\")]")
        if (result.isSuccessful) {
            _quotes.emit(result.body()!!)
        } else {
            Log.d("Kira", "Error")
        }
    }
}