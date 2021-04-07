package com.picpay.desafio.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.picpay.desafio.android.datalocal.UserEntity
import com.picpay.desafio.android.datalocal.UserRepositoryLocal
import kotlinx.coroutines.Dispatchers

class PicPayDAO(private val repositoryLocal: UserRepositoryLocal){

    fun saveUser(idUser: Int, nome: String, userName: String, img: String) =
        liveData(Dispatchers.IO) {
            repositoryLocal.saveUser(UserEntity(0, nome, userName, img))
            emit(true)
        }

    fun getUsers() : LiveData<List<UserEntity>> = liveData(Dispatchers.IO) {
        val users = repositoryLocal.getUsers()
        emit(users)
    }

    fun getUserById(idUser: String) = liveData(Dispatchers.IO) {
            val userById = repositoryLocal.getUsersById(idUser)
            emit(userById)
    }

    fun deleteUser(idUser: Int) = liveData(Dispatchers.IO) {
        repositoryLocal.deleteUser(idUser)
        Log.d("TAG", "deleteUser()")
        emit(true)
    }

    class PicPayLocalViewModelFactory(private val repositoryLocal: UserRepositoryLocal): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PicPayDAO(repositoryLocal) as T
        }
    }
}
