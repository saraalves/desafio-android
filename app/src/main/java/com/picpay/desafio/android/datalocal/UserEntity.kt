package com.picpay.desafio.android.datalocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserData")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var idUser: Int,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var username: String,
    @ColumnInfo
    var img: String
)