package com.mojtaba.nameInfo.feature_name_info.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mojtaba.nameInfo.feature_name_info.data.local.entity.NameInfoEntity

data class NameInfoDto(
    @SerializedName("country")
    val countries: List<NameDetailsDto>,
    @SerializedName("name")
    val name: String
){
    fun toNameInfoEntity():NameInfoEntity{
        return NameInfoEntity(
            countries = countries.map { it.toNameDetails() },
            name = name
        )
    }
}