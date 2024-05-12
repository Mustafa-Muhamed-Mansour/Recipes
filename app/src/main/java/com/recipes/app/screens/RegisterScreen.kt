package com.recipes.app.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.recipes.app.register.pages.RegisterJetpack
import com.recipes.app.register.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

object RegisterScreen: Screen {
    private fun readResolve(): Any = RegisterScreen

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val registerViewModel: RegisterViewModel = koinViewModel()
        RegisterJetpack(registerViewModel = registerViewModel)
    }
}