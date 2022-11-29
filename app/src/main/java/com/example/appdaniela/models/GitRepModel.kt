package com.example.appdaniela.models

import java.io.Serializable

data class GitRepModel(val items: ArrayList<GitRepListInfo>): Serializable

data class GitRepListInfo(
    val id:String,
    val name:String,
    val description:String,
    val private:String,
    val owner:GitOwner
): Serializable

data class GitOwner(
    val login:String,
    val url:String
): Serializable