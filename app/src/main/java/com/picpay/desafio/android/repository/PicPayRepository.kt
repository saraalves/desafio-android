package com.picpay.desafio.android.repository

import com.picpay.desafio.android.datalocal.UserEntity

class PicPayRepository {

    private val client = PicPayService.Service

    suspend fun getList() = client.getUsers()

}