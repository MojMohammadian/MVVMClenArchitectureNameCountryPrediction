package com.mojtaba.nameInfo.feature_name_info.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.mojtaba.nameInfo.feature_name_info.data.util.JsonParser
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameDetails

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromCountriesJson(json: String): List<NameDetails> {
        return jsonParser.fromJson<ArrayList<NameDetails>>(
            json = json,
            type = object : TypeToken<ArrayList<NameDetails>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCountriesJson(nameDetails: List<NameDetails>): String {
        return jsonParser.toJson(
            nameDetails,
            object : TypeToken<ArrayList<NameDetails>>() {}.type
        ) ?: "[]"
    }
}