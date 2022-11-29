package com.example.appdaniela.proxy

import com.example.appdaniela.models.GitRepModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServices {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") q:String = "kotlin",
        @Query("per_page")per_page:String = "10",
        @Query("page")page:String = "1"
    ): Response<GitRepModel>
}