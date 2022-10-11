package com.mojtaba.nameInfo.feature_name_info.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mojtaba.nameInfo.feature_name_info.data.local.entity.NameInfoEntity
import com.mojtaba.nameInfo.feature_name_info.data.remote.dto.NameInfoDto

@Dao
interface NameInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNamedInfo(nameInfoEntity: NameInfoEntity)

    @Query("DELETE FROM nameinfoentity WHERE name = :name")
    suspend fun deleteFromNameInfo(name: String)

    @Query("SELECT * FROM nameinfoentity WHERE name = :name")
    suspend fun getNameInfo(name:String): NameInfoEntity?

}