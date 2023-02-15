package com.example.appdaniela.models

import java.io.Serializable

data class FoodMod(
    val foods:List<Food>
    ): Serializable

data class Food(
    val id:String,
    val name: String,
    val image: String,
    val description: String,
    val coordinates: Coordinates
): Serializable

data class Coordinates(
    val lat:String,
    val alt:String,
): Serializable

