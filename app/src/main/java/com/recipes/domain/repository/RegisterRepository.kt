package com.recipes.domain.repository

interface RegisterRepository {
    suspend fun createAccount(email: String, password: String): Boolean
    suspend fun saveAnPassword(password: String)
    suspend fun saveUserObject(id: String, email: String)

}