package com.example.quotely.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotely.R
import com.example.quotely.ViewModels.CategoryViewModel
import java.util.Locale

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(categories.value.distinct()) {
            Categories(category = it, onClick)
        }
    }
}
@Composable
fun Categories(category: String, onClick: (category: String) -> Unit) {
    val categoryImageRes = getCategoryImageRes(category)

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(category) }
    ) {
        Image(
            painter = painterResource(id = categoryImageRes),
            contentDescription = "Category Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = category,
                fontSize = 16.sp,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@SuppressLint("DefaultLocale")
private fun getCategoryImageRes(category: String): Int {
    val categoryToImageMap = mapOf(
        "AI" to R.drawable.ai,
        "Programming" to R.drawable.programming,
        "Motivation" to R.drawable.motivation,
        "Facts" to R.drawable.facts,
        "Education" to R.drawable.education
    )

    return categoryToImageMap[category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }] ?: R.drawable.bg
}