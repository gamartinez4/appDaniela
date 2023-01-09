package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "roomModel")
data class RoomModel(
    @PrimaryKey(autoGenerate = false) var id: String,
    var name: String,
    var description: String,
    var private: String,
    var login: String,
    var url: String
):Serializable
{
    constructor(): this("","","","","","")
}



fun GitRepListInfo.gitRepListInfo2RoomModel():RoomModel{
    return RoomModel(
        id = id,
        name = name,
        description = description,
        private = private,
        login = owner.login,
        url = owner.url
    )
}
