package com.example.recipe

sealed class Route (val route:String){
    object RecipeScreen: Route("recipeScreen")
    object DetailScreen: Route("detailScreen")
}