package com.example.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.MainViewModel
import eu.tutorials.myrecipeapp.Category


@Composable
fun recipeScreen(modifier: Modifier){
    val recipeView : MainViewModel = viewModel()
    val viewState by recipeView.categoriesState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error !=null->{
                Text(text = "Error Occured !\nCheck your internet connection\n ${viewState.error}",
                    modifier = Modifier.align(Alignment.Center).padding(32.dp))
            }
            else->{
                categoryScreen( viewState.list)
            }


        }
    }
}


@Composable
fun categoryScreen(category : List<Category>){
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(category){
                category ->

            itemScreen(category = category)
        }
    }

}


@Composable
fun itemScreen (category: Category){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .aspectRatio(1f)
        )

        Text(category.strCategory,
            color = Color.Blue,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}