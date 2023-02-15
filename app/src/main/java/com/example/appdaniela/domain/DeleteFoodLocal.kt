package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.DetailsRepository

class DeleteFoodLocal (private val detailsRepository: DetailsRepository) {
    suspend fun execute(id:String) = detailsRepository.deleteFood(id)
}