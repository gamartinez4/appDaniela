package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.DetailsRepository

class GetUserFromUserIdLocal (private val detailsRepository: DetailsRepository) {
    suspend fun execute(userId: String) = detailsRepository.getUsersFromDataBase(userId)
}