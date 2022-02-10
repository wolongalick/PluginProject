package com.alick.plugin

import com.google.gson.Gson

object JsonUtils {
    private val gson: Gson by lazy {
        Gson()
    }

    fun <T : Any> parseJson2Bean(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    fun parseBean2Json(any: Any): String {
        return gson.toJson(any)
    }

}