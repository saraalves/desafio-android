package com.picpay.desafio.android.datalocal

class UserRepositoryLocal(private  val userDAO: UserDAO) {

    suspend fun saveUser(userEntity: UserEntity) = userDAO.saveUser(userEntity)
    suspend fun getUsers(idUser: String) = userDAO.getUsers(idUser)
    suspend fun deleteUser(idUser: Int) = userDAO.deleteUser(idUser)
}