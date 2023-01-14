package com.example.appdaniela.remote

import com.example.appdaniela.models.Comment
import com.example.appdaniela.models.Result
import com.example.appdaniela.models.User
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.proxy.handlers.parseError
import java.lang.Exception

class DetailsRepoDataSource(private val apiServices: ApiServices){


    suspend fun getComments(postId:String): Result<ArrayList<Comment>> {
        try {
            apiServices.getComments(postId = postId).run {
                return if (isSuccessful && body() != null) Result.Success(body() as ArrayList<Comment>)
                else Result.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Result.Failure(e)
        }
    }

    suspend fun getUsers(userId:String): Result<ArrayList<User>> {
        try {
            apiServices.getUsers(id = userId).run {
                return if (isSuccessful && body() != null) Result.Success(body() as ArrayList<User>)
                else Result.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Result.Failure(e)
        }
    }
}