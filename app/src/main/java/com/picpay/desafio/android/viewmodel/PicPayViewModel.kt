package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

class PicPayViewModel(private val repository: PicPayRepository, private var picPayDAO: PicPayDAO,
                      override val coroutineContext: CoroutineContext, application: Application
) : AndroidViewModel(application),
    KoinComponent, CoroutineScope {

    private var _listUsers : List<User> = listOf()

    fun getList() = liveData(Dispatchers.IO) {
        if(picPayDAO.getUsers().value?.isNullOrEmpty() == true) {
            val response = repository.getList()
            _listUsers = response
            _listUsers.forEach {
                picPayDAO.saveUser(it.id, it.name, it.username, it.img)
            }
            emit(_listUsers)
        } else {
            val usersCasted = arrayListOf<User>()
            val localUsers = picPayDAO.getUsers().value
            localUsers?.forEach {
                usersCasted.add(User(it.img, it.name, it.idUser, it.username))
            }
            emit(usersCasted)
        }
    }

}