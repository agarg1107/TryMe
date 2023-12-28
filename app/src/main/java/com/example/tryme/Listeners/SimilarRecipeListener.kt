package com.example.tryme.Listeners

import com.example.tryme.Models.GetSimilarRecipe
import com.example.tryme.Models.RandomRecipeApiCall

interface SimilarRecipeListener {
    fun didFetch(getSimilarRecipe: List<GetSimilarRecipe>,message: String)
    fun didError(message: String)

}

