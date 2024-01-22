package com.example.quotely.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotely.ViewModels.DetailViewModel
import com.example.quotely.ui.theme.Purple80
import com.example.quotely.ui.theme.PurpleGrey40

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val quotes = detailViewModel.quotes.collectAsState()

    CompositionLocalProvider(
        LocalContentColor provides contentColorFor(MaterialTheme.colorScheme.contentColorFor(PurpleGrey40)),
        content = {
            LazyColumn {
                items(quotes.value) {
                    QuoteListItem(quote = it.text)
                }
            }
        }
    )
}



@Composable
fun QuoteListItem(quote: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        content = {
            Text(
                text = quote,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = LocalContentColor.current
                ),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    )
}