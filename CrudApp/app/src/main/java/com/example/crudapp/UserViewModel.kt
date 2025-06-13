package com.example.crudapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserViewModel(aplication: Application) : AndroidViewModel(aplication) {
    private val repository: UserRepository
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        observeUsers()
    }

    private fun observeUsers() {
        viewModelScope.launch {
            repository.getAll().collectLatest { userList ->
                _users.value = userList
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch { repository.insertUser(user) }
    }

    fun updateUser(user: User) {
        viewModelScope.launch { repository.updateUser(user) }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch { repository.delete(user) }
    }
}