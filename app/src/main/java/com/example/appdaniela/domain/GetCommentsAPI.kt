package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class GetAllCommentsAPI (private val introRepository: IntroRepository) {
    suspend fun execute() = introRepository.getComments()
}