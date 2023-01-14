package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "roomModel")
data class RoomModel(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var title:String = "",
    var body:String = "",
    var favourite:Boolean = false,
):Serializable

fun Post.gitRepListInfo2RoomModel():RoomModel{
    return RoomModel(
        id = id,
        title = title?:"" ,
        body = body?:""
    )
}
