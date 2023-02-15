package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.DetailsRepository

class SetFoodDtoLocal(private val detailsRepository: DetailsRepository) {
    suspend fun execute(favourite:Boolean,id:String) = detailsRepository.setFood(favourite,id)
}