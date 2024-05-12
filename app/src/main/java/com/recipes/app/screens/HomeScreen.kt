package com.recipes.app.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.recipes.app.home.pages.HomeJetpack
import com.recipes.app.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeScreen: Screen {
    private fun readResolve(): Any = HomeScreen
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinViewModel()
        HomeJetpack(homeViewModel = viewModel)
    }
}