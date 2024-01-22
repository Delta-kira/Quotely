package com.example.quotely.ViewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotely.Models.QuotesListItem
import com.example.quotely.Repository.QuotelyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: QuotelyRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val quotes: StateFlow<List<QuotesListItem>>
        get() = repository.quotes

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "AI"
            repository.getQuotes(category)
        }
    }
}