package com.example.appdaniela.models

import java.io.Serializable

data class FoodMod(
    val foods:ArrayList<Food>
    ): Serializable

data class Food(
    val id:String,
    val name: String,
    val image: String,
    val description: String,
    val coordinates: Coordinates
)

data class Coordinates(
    val lat:String,
    val alt:String,
)

