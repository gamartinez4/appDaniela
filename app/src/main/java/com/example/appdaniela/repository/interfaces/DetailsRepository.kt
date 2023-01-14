package com.example.appdaniela.repository.interfaces

import com.example.appdaniela.models.Post
import com.example.appdaniela.models.User

interface DetailsRepository {

    suspend fun setRoomModel(favourite:Boolean,id:String)

    suspend fun getComments():ArrayList<Post>

    suspend fun getUsers():ArrayList<User>
}