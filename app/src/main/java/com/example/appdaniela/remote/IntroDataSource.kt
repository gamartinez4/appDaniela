package com.example.appdaniela.remote

import com.example.appdaniela.models.*
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.proxy.handlers.parseError
import java.lang.Exception

class IntroDataSource (private val apiServices: ApiServices){

    suspend fun getPosts(start:String):Results<ArrayList<Post>>{
        try {
            apiServices.getPosts(start = start).run {
                return if (isSuccessful && body() != null) Results.Success(body() as ArrayList<Post>)
                else Results.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Results.Failure(e)
        }
    }

    suspend fun getComments(): Results<List<CommentDto>> {
        try {
            apiServices.getComments().run {
                return if (isSuccessful && body() != null) Results.Success(
                    (body() as ArrayList<Comment>).map { it.comment2commentDto() }
                )
                else Results.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Results.Failure(e)
        }
    }

    suspend fun getUsers(): Results<List<UserDto>> {
        try {
            apiServices.getUsers().run {
                return if (isSuccessful && body() != null) Results.Success(
                    (body() as ArrayList<User>).map { it.user2userDto() }
                )
                else Results.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Results.Failure(e)
        }
    }
}
