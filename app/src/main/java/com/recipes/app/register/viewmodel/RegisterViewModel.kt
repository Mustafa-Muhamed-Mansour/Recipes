package com.recipes.app.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipes.domain.usecase.RegisterAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class RegisterViewModel(
    private val registerAccountUseCase: RegisterAccountUseCase
) : ViewModel(), KoinComponent {


    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _register: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val register: StateFlow<Boolean?> get() = _register

    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            try {
                _register.value = registerAccountUseCase.createAnAccount(email = email, password = password)
            } catch (e: Exception) {
                _errorMessage.value = e.message.toString()
            }
        }
    }

    fun saveAnPassword(password: String) {
        viewModelScope.launch {
            registerAccountUseCase.saveAnPassword(password = password)
        }
    }

    fun saveUserObject(id: String, email: String) {
        viewModelScope.launch {
            registerAccountUseCase.saveUserObject(id = id, email = email)
        }
    }
}