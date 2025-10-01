package com.example.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.myrecipeapp.Category

@Composable
fun categoryDetailScreen(category: Category){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(category.strCategory,
//            color = Color.Green,
            fontSize = 32.sp,
            textAlign = TextAlign.Center

        )
//        Spacer(Modifier.height(8.dp))
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Thumbnail Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)   // makes the image a square based on width
        )

//        Spacer(modifier = Modifier.height(8.dp))
        Text(category.strCategoryDescription,
            Modifier.verticalScroll(rememberScrollState())
                .padding(16.dp),
            textAlign = TextAlign.Justify

        )
    }
}