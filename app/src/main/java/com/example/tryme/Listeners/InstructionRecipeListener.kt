package com.example.tryme.Listeners

import android.os.Message
import com.example.tryme.Models.getInstructions

interface InstructionRecipeListener {
    fun didFetch(getInstructions: List<getInstructions>,message: String)
    fun didError(message: String)

}