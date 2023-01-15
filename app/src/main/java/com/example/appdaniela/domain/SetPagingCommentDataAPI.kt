package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.repository.interfaces.IntroRepository

class SetPagingCommentDataAPI (private val detailsRepository: DetailsRepository) {
    suspend fun execute(postId:String) = detailsRepository.setPagingCommentsData(postId)
}