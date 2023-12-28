package com.example.tryme.Models

class Step(
    val number: Long,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
    val length: Length?,
)