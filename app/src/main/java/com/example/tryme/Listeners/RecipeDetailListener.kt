package com.example.tryme.Listeners

import com.example.tryme.Models.RecipeDetailResponse

interface RecipeDetailListener {
    fun didFetch(recipeDetailResponse: RecipeDetailResponse,message : String)
    fun didError(message: String)
}