package com.example.appdaniela.repository.interfaces

interface DetailsRepository {

    suspend fun setFood(favourite:Boolean, id:String)

    suspend fun deleteFood(id:String)
}