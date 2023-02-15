package com.example.appdaniela.remote

import com.example.appdaniela.models.*
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.proxy.handlers.parseError
import java.lang.Exception

class IntroDataSource (private val apiServices: ApiServices){

    suspend fun getFoods():Results<ArrayList<Food>>{
        try {
            apiServices.getPosts().run {
                return if (isSuccessful && body() != null) Results.Success((body() as FoodMod).foods as ArrayList<Food>)
                else Results.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Results.Failure(e)
        }
    }

}
