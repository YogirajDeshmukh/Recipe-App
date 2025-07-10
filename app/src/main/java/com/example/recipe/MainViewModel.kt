package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.recipe.recipeService
import eu.tutorials.myrecipeapp.Category
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    init {
        fetchCategories()
    }

    data class RecipeState (
        val loading:Boolean=true,
        val list:List<Category> = emptyList(),
        val error:String?=null
    )
    private var _categoriesState = mutableStateOf(RecipeState())

    val categoriesState:State<RecipeState> = _categoriesState

    private fun fetchCategories(){
        viewModelScope.launch{
            try {
                val responses = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = responses.categories,
                    error = null,
                )

            }catch (e:Exception){
                _categoriesState.value= _categoriesState.value.copy(
                    loading = false,
                    error = "Error in fetching catrgories ${e.message}"
                )
            }
        }
    }

}