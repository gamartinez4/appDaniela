package com.example.appdaniela.repository.impl

import com.example.appdaniela.models.Post
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.models.User
import com.example.appdaniela.remote.DetailsRepoDataSource
import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao

class DetailsRepositoryImpl(
    private val roomModelDao: RoomModelDao,
    // detailsRepoDataSource: DetailsRepoDataSource
) : DetailsRepository {

    override suspend fun setRoomModel(favourite:Boolean,id:String) {
        roomModelDao.insertElement(favourite,id)
    }

    override suspend fun getComments(): ArrayList<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): ArrayList<User> {
        TODO("Not yet implemented")
    }

}