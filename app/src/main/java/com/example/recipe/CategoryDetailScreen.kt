package com.example.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.myrecipeapp.Category

@Composable
fun categoryDetailScreen(category: Category){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Text(category.strCategory)
        Spacer(Modifier.height(8.dp))
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Thumbnain Image",
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(category.strCategoryDescription,
            Modifier.verticalScroll(rememberScrollState()),
            textAlign = TextAlign.Justify

        )
    }
}