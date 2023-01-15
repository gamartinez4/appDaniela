package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class GetAllUsersAPI(private val introRepository: IntroRepository) {
    suspend fun execute() = introRepository.getUsers()
}