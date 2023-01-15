package com.example.appdaniela.domain

import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.repository.interfaces.IntroRepository

class SetCommentsLocal(private val introRepository: IntroRepository) {
        suspend fun execute(comments:List<CommentDto>) = introRepository.setComments(comments)
}