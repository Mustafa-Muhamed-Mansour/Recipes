package com.recipes.app.activities.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.recipes.app.screens.HomeScreen

class JetpackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(screen = HomeScreen)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRecipes() {
    Navigator(screen = HomeScreen)
}