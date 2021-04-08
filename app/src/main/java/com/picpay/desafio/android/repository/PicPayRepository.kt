package com.picpay.desafio.android.repository

import com.picpay.desafio.android.datalocal.UserEntity
import com.picpay.desafio.android.model.User

class PicPayRepository(private val api: PicPayService) {

// suspend por causa do coroutines, pq se der o problema com o caminha responsavel
// com a chamada, ele suspende e para e não crasha o app, ele só para o que tá fazendo

    suspend fun getList(): List<User>? {
        return api.getUsers()
    }
}