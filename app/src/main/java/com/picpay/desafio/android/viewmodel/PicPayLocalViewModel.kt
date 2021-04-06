package com.picpay.desafio.android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.picpay.desafio.android.datalocal.UserEntity
import com.picpay.desafio.android.datalocal.UserRepositoryLocal
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.Dispatchers

class PicPayLocalViewModel(private val repositoryLocal: UserRepositoryLocal) : ViewModel() {

    fun addUser(idUser: Int, nome: String, userName: String, img: String) =
        liveData(Dispatchers.IO) {
            repositoryLocal.saveUser(UserEntity(0, nome, userName, img))
            emit(true)
        }

    fun deleteUser(idUser: Int) = liveData(Dispatchers.IO) {
        repositoryLocal.deleteUser(idUser)
        Log.d("TAG", "deleteUser()")
        emit(true)
    }

    class PicPayLocalViewModelFactory(private val repositoryLocal: UserRepositoryLocal): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PicPayLocalViewModel(repositoryLocal) as T
        }
    }
}
