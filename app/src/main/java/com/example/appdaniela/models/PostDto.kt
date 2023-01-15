package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
data class PostDto(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var title:String = "",
    var body:String = "",
    var userId:String = "",
    var favourite:Boolean = false,
):Serializable

fun Post.post2postDto():PostDto{
    return PostDto(
        id = id,
        title = title?:"" ,
        body = body?:"",
        userId = userId?:""
    )
}
