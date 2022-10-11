package com.mojtaba.nameInfo.feature_name_info.data.util

import java.lang.reflect.Type


interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?

}