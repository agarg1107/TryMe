package com.example.tryme.Listeners

import com.example.tryme.Models.RandomRecipeApiCall

interface RandomRecipeListener {
    fun didFetch(rendomRecipeApiCall: RandomRecipeApiCall, message : String);
    fun didError(message: String);
}