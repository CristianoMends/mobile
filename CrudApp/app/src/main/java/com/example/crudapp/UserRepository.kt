package com.example.crudapp
















class UserRepository (private val userDAO: UserDAO){
    suspend fun insertUser(user: User) = userDAO.insertUser(user)

    fun getAll() = userDAO.getAll()

    suspend fun updateUser(user: User) = userDAO.update(user)

    suspend fun delete(user: User) = userDAO.delete(user)
}