@file:JvmName("FoodModKt")

package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "foods")
data class FoodModDto(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    val name: String,
    val image: String,
    val description: String,
    var favourite: Boolean = false,
    val lat: String,
    val alt: String
):Serializable

fun Food.foodMod2foodModDto():FoodModDto{
    return FoodModDto(
        id = id,
        name = name?:"" ,
        image = image?:"",
        description = description?:"",
        lat = coordinates.lat?:"",
        alt = coordinates.alt?:""
    )
}
