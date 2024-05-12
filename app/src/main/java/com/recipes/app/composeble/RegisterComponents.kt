package com.recipes.app.composeble

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.recipes.R
import com.recipes.app.register.viewmodel.RegisterViewModel
import com.recipes.app.utils.CryptoData
import com.recipes.data.datasource.local.datastore.PasswordDataStore
import com.recipes.data.datasource.local.datastore.PrefDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Base64

private val cryptoData by lazy { CryptoData() }

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RegisterComponents(registerViewModel: RegisterViewModel) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 5.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateAccountToUser(
            addValue = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "E-mail",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.black)
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email, contentDescription = "icon email")
            }
        )

        CreateAccountToUser(
            addValue = password,
            label = {
                Text(
                    text = "Password",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.black)
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Lock, contentDescription = "icon password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val imagePassword =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val descriptionPassword = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = imagePassword, contentDescription = descriptionPassword)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val dataStore = PasswordDataStore(context)
        val pass = dataStore.getPassword.collectAsState(initial = "null")
        val id = FirebaseAuth.getInstance().uid.toString()

        Button(
            onClick = {
                createAccount(
                    email.value,
                    password.value,
                    context,
                    scope,
                    registerViewModel
                )
                saveUserPassword(
                    password.value,
                    scope,
                    registerViewModel
                )
                saveUserObject(
                    id,
                    email.value,
                    registerViewModel
                )

            },
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.black),
                containerColor = colorResource(id = R.color.black)
            ),
        ) {
            Text(
                text = "Register",
                fontSize = 17.sp,
                color = colorResource(id = R.color.white)
            )
        }

        Text(
            text = "Password:- ${pass.value.toString()}",
            modifier = Modifier
                .clickable {
                    convertPasswordFromEncryptToDecrypt(scope, context)
                }
                .padding(all = 10.dp),
            fontSize = 20.sp,
            color = colorResource(id = R.color.black)
        )
    }
}

private fun createAccount(
    email: String,
    password: String,
    context: Context,
    scope: CoroutineScope,
    registerViewModel: RegisterViewModel
) {
    registerViewModel.createAccount(email, password)
    scope.launch {
        registerViewModel.register.collect {
            if (it == true) {
                Toast.makeText(context, "Done, add user to Database", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun saveUserPassword(
    password: String,
    scope: CoroutineScope,
    registerViewModel: RegisterViewModel
) {
    scope.launch {
        val byte = password.toByteArray()
        val encryptedText = Base64.getEncoder().encodeToString(cryptoData.encrypt(byte = byte))
        registerViewModel.saveAnPassword(password = encryptedText)
    }
}

private fun saveUserObject(
    id: String,
    email: String,
    registerViewModel: RegisterViewModel
) {
    registerViewModel.saveUserObject(id = id, email = email)
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SetTextI18n")
private fun convertPasswordFromEncryptToDecrypt(
    scope: CoroutineScope,
    context: Context
) {
    scope.launch {
        context.PrefDataStore.data.collect {
            val password = it[PasswordDataStore.USER_PASSWORD] ?: "null"
            val originalText =
                cryptoData.decrypt(Base64.getDecoder().decode(password))?.decodeToString()
            Toast.makeText(context, originalText, Toast.LENGTH_SHORT).show()
        }
    }
}