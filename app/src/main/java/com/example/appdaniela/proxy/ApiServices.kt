package com.example.appdaniela.proxy

import com.example.appdaniela.models.Comment
import com.example.appdaniela.models.Post
import com.example.appdaniela.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("posts")
    suspend fun getPosts(
        @Query("posts") posts: String = "1",
        @Query("_start") start: String = "0",
        @Query("_limit") limit: String = "10",
    ): Response<ArrayList<Post>>

    @GET("comments")
    suspend fun getComments(): Response<ArrayList<Comment>>

    @GET("users")
    suspend fun getUsers(): Response<ArrayList<User>>

}