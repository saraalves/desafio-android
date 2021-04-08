package com.picpay.desafio.android.datalocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert
    suspend fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserData WHERE idUser = :idUser")
    suspend fun getUserByID(idUser: String): UserEntity

    @Query("SELECT * FROM UserData")
    suspend fun getUsers(): List<UserEntity>

    @Query("DELETE FROM UserData WHERE idUser = :idUser")
    suspend fun deleteUser(idUser: Int)
}