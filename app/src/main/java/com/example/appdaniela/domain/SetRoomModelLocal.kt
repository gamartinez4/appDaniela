package com.example.appdaniela.domain

import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.repository.interfaces.DetailsRepository

class SetRoomModelLocal(private var detailsRepository: DetailsRepository) {
    suspend fun execute(roomModel: RoomModel) = detailsRepository.setRoomModel(roomModel)
}