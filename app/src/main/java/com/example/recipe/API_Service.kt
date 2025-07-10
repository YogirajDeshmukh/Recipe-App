package com.example.recipe

import eu.tutorials.myrecipeapp.CategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


 val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val recipeService = retrofit.create(API_Service::class.java)


interface API_Service {
    @GET("categories.php")
    suspend fun getCategories (): CategoriesResponse
}
