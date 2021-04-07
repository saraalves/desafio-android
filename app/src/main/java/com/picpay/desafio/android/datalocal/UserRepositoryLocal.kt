package com.picpay.desafio.android.datalocal

class UserRepositoryLocal(private  val userDAO: UserDAO) {

    suspend fun saveUser(userEntity: UserEntity) = userDAO.saveUser(userEntity)
    suspend fun getUsersById(idUser: String) = userDAO.getUserByID(idUser)
    suspend fun getUsers() : List<UserEntity> = userDAO.getUsers()
    suspend fun deleteUser(idUser: Int) = userDAO.deleteUser(idUser)
}