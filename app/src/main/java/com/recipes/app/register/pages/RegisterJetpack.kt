package com.recipes.app.register.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.recipes.app.composeble.RegisterComponents
import com.recipes.app.register.viewmodel.RegisterViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterJetpack(registerViewModel: RegisterViewModel) {
    RegisterContentJetpack(registerViewModel = registerViewModel)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterContentJetpack(registerViewModel: RegisterViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RegisterComponents(registerViewModel = registerViewModel)
    }
}