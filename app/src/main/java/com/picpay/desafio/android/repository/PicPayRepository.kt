package com.picpay.desafio.android.repository

class PicPayRepository {

    private val client = PicPayService.Service

    suspend fun getList() = client.getUsers()

}