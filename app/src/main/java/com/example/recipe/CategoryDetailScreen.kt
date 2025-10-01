package com.example.recipe

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.myrecipeapp.Category

@Composable
fun categoryDetailScreen(category: Category){
    Box(modifier = Modifier.fillMaxSize()) {

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
            painter = painterResource(id = R.drawable.bg_img),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f), // 0 = fully transparent, 1 = fully opaque
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                category.strCategory,
//            color = Color.Green,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
//                textDecoration = TextDecoration.Underline,
                modifier = Modifier.border(
                        BorderStroke(4.dp, animatedColor),
                shape = RoundedCornerShape(15)
            ).padding(16.dp)

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
            Text(
                text = category.strCategoryDescription,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .border(
                        BorderStroke(2.dp, Color.Gray), // specify width and color
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}