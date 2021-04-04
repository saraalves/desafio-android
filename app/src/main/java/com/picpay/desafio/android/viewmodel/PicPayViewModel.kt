package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.Dispatchers

class PicPayViewModel(private val repository: PicPayRepository) : ViewModel() {

    private var _listUsers : List<User> = listOf()

    fun getList() = liveData(Dispatchers.IO) {
        val response = repository.getList()
        _listUsers = response

        emit(_listUsers)
    }

    class PicPayViewModelFactory(private val repository: PicPayRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return  PicPayViewModel(repository) as T
        }

    }
}