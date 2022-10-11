package com.mojtaba.nameInfo.feature_name_info.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameDetails
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo

data class NameDetailsDto(
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("probability")
    val probability: Double
){
    fun toNameDetails(): NameDetails{
        return NameDetails(
            countryId, probability
        )
    }
}