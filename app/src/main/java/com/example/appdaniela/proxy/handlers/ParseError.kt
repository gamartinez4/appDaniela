package com.example.appdaniela.proxy.handlers

import okhttp3.ResponseBody
import org.json.JSONObject

fun parseError(responseBody: ResponseBody?): ErrorMessage {
    try {
        val response = responseBody?.string()
        val jsonObject = JSONObject(response!!)
        var message = jsonObject.getString("message")
        try {
            val jsonObjectErrors = jsonObject.getJSONObject("errors")
            val keys: Iterator<String> = jsonObjectErrors.keys()
            while (keys.hasNext()) {
                val jsonArray = jsonObjectErrors.getJSONArray(keys.next())
                for (i in 1..jsonArray.length()) {
                    message += "\n${jsonArray.getString(i - 1)}"
                }
            }
        } finally {
            return ErrorMessage(message = message)
        }
    } catch (e: Exception) {
        return ErrorMessage(message = "Ha ocurrido un error leyendo la respuesta")
    }
}