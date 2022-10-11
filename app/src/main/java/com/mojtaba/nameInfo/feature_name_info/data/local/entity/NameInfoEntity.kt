package com.mojtaba.nameInfo.feature_name_info.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameDetails
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo

@Entity
data class NameInfoEntity(
    val countries: List<NameDetails>,
    @PrimaryKey(autoGenerate = false)
    val name: String,
){
    fun toNameInfo() : NameInfo{
        return NameInfo(
            countries = countries,
            name = name
        )
    }
    }
