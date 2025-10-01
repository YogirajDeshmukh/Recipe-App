package com.example.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myrecipeapp.MainViewModel
import eu.tutorials.myrecipeapp.Category
import androidx.compose.ui.Modifier  // ✅ Correct import

@Composable
fun recipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Route.RecipeScreen.route) {
        composable(route = Route.RecipeScreen.route) {
            recipeScreen(
                navigateToDetailScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(Route.DetailScreen.route)
                },
                viewState = viewstate
            )
        }
        composable(route = Route.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category("", "", "", "")
            categoryDetailScreen(category)
        }
    }
}
