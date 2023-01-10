package com.example.appdaniela.repository.impl

import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao

class DetailsRepositoryImpl( private val roomModelDao: RoomModelDao) : DetailsRepository {
    override suspend fun setRoomModel(roomModel: RoomModel) {
        roomModelDao.insertElement(roomModel)
    }

}