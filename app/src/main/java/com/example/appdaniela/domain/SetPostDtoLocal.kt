package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.DetailsRepository

class SetPostDtoLocal(private val detailsRepository: DetailsRepository) {
    suspend fun execute(favourite:Boolean,id:String) = detailsRepository.setPost(favourite,id)
}