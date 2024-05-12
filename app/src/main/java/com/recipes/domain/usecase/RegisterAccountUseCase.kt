package com.recipes.domain.usecase

import com.recipes.domain.repository.RegisterRepository

class RegisterAccountUseCase(
    private val registerRepository: RegisterRepository
) {
    suspend fun createAnAccount(email: String, password: String) = registerRepository.createAccount(email = email, password = password)
    suspend fun saveAnPassword(password: String) = registerRepository.saveAnPassword(password = password)
    suspend fun saveUserObject(id: String, email: String) = registerRepository.saveUserObject(id = id, email = email)
}