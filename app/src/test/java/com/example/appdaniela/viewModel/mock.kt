package com.example.appdaniela.viewModel

import androidx.paging.PagingData
import com.example.appdaniela.models.PostDto

val mockedGit = listOf(
    PostDto(
        id="1",
        title = "Titlo1",
        body = "body1",
        favourite = false
    ),PostDto(
        id="2",
        title = "Titlo2",
        body = "body2",
        favourite = true
    ),PostDto(
        id="3",
        title = "Titlo3",
        body = "body3",
        favourite = false
    )
)

val mockedGitData = PagingData.from(mockedGit)
