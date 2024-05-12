package com.recipes.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.recipes.data.datasource.local.datastore.PasswordDataStore
import com.recipes.data.datasource.local.datastore.PrefDataStore
import com.recipes.data.datasource.local.datastore.ProtoDataStore
import com.recipes.domain.entity.UserModel
import com.recipes.domain.repository.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterRepositoryIml(
    private val context: Context
) : RegisterRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    private val _error: MutableStateFlow<String> = MutableStateFlow("")
    val error: StateFlow<String> get() = _error


    private val _boolean: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val boolean: StateFlow<Boolean?> get() = _boolean

    override suspend fun createAccount(email: String, password: String): Boolean {
        firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            val id = firebaseAuth.uid.toString()
            val userModel = UserModel(
                id = id, email = email
            )
            database
                .child("Users")
                .child(id)
                .setValue(userModel)

            _boolean.value = true

        }.addOnFailureListener {
            _error.value = it.toString()

            _boolean.value = false
        }
        return _boolean.value == true
    }

    override suspend fun saveAnPassword(password: String) {
        context.PrefDataStore.edit {
            it[PasswordDataStore.USER_PASSWORD] = password
        }
    }

    override suspend fun saveUserObject(id: String, email: String) {
        context.ProtoDataStore.updateData {
            it.copy(id = id, email = email)
        }
    }

}