package com.example.appdaniela.models

import java.io.Serializable

data class ResponseModel(val data: Data): Serializable

data class Data(
    val results:ArrayList<Comic>
): Serializable

data class Comic(
    val id:String,
    val title:String?,
    val description:String? ,
    val images:List<ImageModel>?,
    val prices:List<Price>?
): Serializable

data class ImageModel(
    val path:String,
    val extension:String
): Serializable

data class Price(
    val type:String,
    val price:String
): Serializable

