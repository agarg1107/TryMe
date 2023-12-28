package com.example.tryme.Models

class Equipment(
    val id: Long,
    val name: String,
    val localizedName: String,
    val image: String,
    val temperature: Temperature?,
)