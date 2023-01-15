package com.example.appdaniela.domain

import com.example.appdaniela.models.UserDto
import com.example.appdaniela.repository.interfaces.IntroRepository

class SetUsersLocal(private val introRepository: IntroRepository) {
    suspend fun execute(users:List<UserDto>) = introRepository.setUsers(users)
}