package com.example.appdaniela.repository.interfaces

import com.example.appdaniela.models.RoomModel

interface DetailsRepository {
    suspend fun setRoomModel(roomModel: RoomModel)
}