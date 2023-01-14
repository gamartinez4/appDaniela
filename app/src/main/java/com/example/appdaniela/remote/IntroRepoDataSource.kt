package com.example.appdaniela.remote

import com.example.appdaniela.models.Post
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.models.Result
import com.example.appdaniela.proxy.handlers.parseError
import java.lang.Exception

class IntroRepoDataSource (private val apiServices: ApiServices){

    suspend fun getPosts(start:String):Result<ArrayList<Post>>{
        try {
            apiServices.getPosts(start = start).run {
                return if (isSuccessful && body() != null) Result.Success(body() as ArrayList<Post>)
                else Result.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Result.Failure(e)
        }
    }
}
