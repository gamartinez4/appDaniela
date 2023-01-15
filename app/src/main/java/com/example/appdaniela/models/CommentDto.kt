package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentDto(
    @PrimaryKey(autoGenerate = false) val id:String = "",
    val postId: String = "",
    val body:String = ""
    )

fun Comment.comment2commentDto():CommentDto{
    return CommentDto(
        id = id,
        postId = postId?:"" ,
        body = body?:""
    )
}