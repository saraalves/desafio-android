package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.NetworkUtils
import retrofit2.http.GET


interface PicPayService {

    @GET("tests/mobdev/users")
    suspend fun getUsers(): List<User>

}