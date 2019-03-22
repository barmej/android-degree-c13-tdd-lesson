package barmej.com.healthyfamily.repository

import androidx.annotation.WorkerThread
import barmej.com.healthyfamily.model.User
import barmej.com.healthyfamily.model.UserDAO

class UserRepository(private val userDAO: UserDAO) {
    val allUsers = userDAO.getAllUsers()

    @WorkerThread
    fun insert(user: User) {
        userDAO.insertUser(user)
    }
}