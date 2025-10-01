package com.example.recipe

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.MainViewModel
import eu.tutorials.myrecipeapp.Category


@Composable
fun recipeScreen(
                modifier: Modifier = Modifier,
                 navigateToDetailScreen: (Category) -> Unit,
                 viewState: MainViewModel.RecipeState
                 ) {



    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(
                    text = "Error Occured !\nCheck your internet connection\n\n ${viewState.error}",
                    modifier
                        .align(Alignment.Center)
                        .padding(32.dp),
                    Color.Red
                )
            }

            else -> {
                categoryScreen(viewState.list
                                 ,navigateToDetailScreen)
            }


        }
    }
}


@Composable
fun categoryScreen(category: List<Category>,
                   navigateToDetailScreen: (Category) -> Unit
                   ) {
//    Image(
//        painter = painterResource(id = R.drawable.images),
//        modifier = Modifier
//            .fillMaxSize()
//            .blur(32.dp),
//        contentDescription = null,
//        contentScale = ContentScale.Crop
//    )

    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(category) { category ->

            categoryItem(category = category,navigateToDetailScreen)
        }
    }

}


@Composable
fun categoryItem(category: Category,
                 navigateToDetailScreen : (Category)-> Unit
                 ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .clickable{navigateToDetailScreen(category)}
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔁 Animate border color
        val infiniteTransition = rememberInfiniteTransition(label = "borderAnim")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = Color(0xFFFFD580),
            targetValue = Color(0xFFFFA726),
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 2000
                    Color(0xFFDDD6CD) at 0
                    Color(0xFFFAFAFA) at 1000
                    Color(0xFFD0A792) at 2000
                },
                repeatMode = RepeatMode.Reverse
            ),
            label = "color"
        )


            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .aspectRatio(1f)
                    .border(
                        BorderStroke(4.dp, animatedColor),
                        shape = RoundedCornerShape(15)
                    )
                    .clip(RoundedCornerShape(16.dp))
            )

            Text(
                category.strCategory,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp
                )

            )
            Button(onClick = {navigateToDetailScreen(category)},
                modifier = Modifier
                ) {
                Text("View Recipe",
                    color = Color.Yellow
                    )}

        }
    }

