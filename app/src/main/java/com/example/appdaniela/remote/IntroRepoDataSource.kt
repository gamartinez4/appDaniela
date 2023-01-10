package com.example.appdaniela.remote

import com.example.appdaniela.models.ResponseModel
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.models.Result
import com.example.appdaniela.proxy.handlers.parseError
import java.lang.Exception

class IntroRepoDataSource (private val apiServices: ApiServices){

    suspend fun getComicsSource(offset:String):Result<ResponseModel>{
        try {
            apiServices.getComics(offset = offset).run {
                return if (isSuccessful && body() != null) Result.Success(body() as ResponseModel)
                else Result.Failure(Exception(parseError(errorBody()).message))
            }
        }catch (e: Exception) {
            return Result.Failure(e)
        }
    }

}
