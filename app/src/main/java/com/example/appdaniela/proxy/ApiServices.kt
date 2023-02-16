package com.example.appdaniela.proxy

import com.example.appdaniela.models.FoodMod
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("foods")
    suspend fun getPosts(): Response<FoodMod>

}