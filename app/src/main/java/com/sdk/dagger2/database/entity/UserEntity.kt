package com.sdk.dagger2.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sdk.dagger2.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val username: String,
    val email: String
)