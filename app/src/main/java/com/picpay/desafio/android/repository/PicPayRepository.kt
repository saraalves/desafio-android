package com.picpay.desafio.android.repository

import com.picpay.desafio.android.datalocal.UserEntity
import com.picpay.desafio.android.model.User

class PicPayRepository(private val api: PicPayService) {

    suspend fun getList(): List<User>? {
        return api.getUsers()
    }
}