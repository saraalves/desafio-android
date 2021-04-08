package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.picpay.desafio.android.datalocal.UserDAO
import com.picpay.desafio.android.datalocal.UserEntity
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

class PicPayViewModel(private val repository: PicPayRepository, private var userDAO: UserDAO
) : ViewModel(), KoinComponent {

    private var _listUsers : List<User> = listOf()

    fun getList() = liveData(Dispatchers.IO) {
        if (userDAO.getUsers().isNullOrEmpty()) {
            val response = repository.getList()
            response?.let { _listUsers = it }
            _listUsers.forEach {
                userDAO.saveUser(UserEntity(0, it.name, it.username, it.img))
            }
            emit(_listUsers)
        } else {
            val usersCasted = arrayListOf<User>()
            val localUsers = userDAO.getUsers()
            localUsers.forEach {
                usersCasted.add(User(it.img, it.name, it.idUser, it.username))
            }
            emit(usersCasted)
        }
    }

}