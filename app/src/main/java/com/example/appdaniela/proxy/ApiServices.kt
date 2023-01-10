package com.example.appdaniela.proxy

import com.example.appdaniela.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("ts") ts:String = "1",
        @Query("apikey")apikey:String = "495792633b7cf8c955b397bcdb5cec68",
        @Query("hash")hash:String = "a711ae5ab096dcc200f543986fcd19ec",
        @Query("offset")offset:String = "0",
        @Query("limit")limit:String = "10"
    ): Response<ResponseModel>
}