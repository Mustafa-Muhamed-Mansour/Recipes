package com.recipes.app.home.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.recipes.R
import com.recipes.app.composeble.CheckDataOfHome
import com.recipes.app.home.viewmodel.HomeViewModel
import com.recipes.app.screens.RegisterScreen


@Composable
fun HomeJetpack(homeViewModel: HomeViewModel) {
    HomeContentJetpack(homeViewModel)
}


@Composable
fun HomeContentJetpack(homeViewModel: HomeViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CheckDataOfHome(homeViewModel = homeViewModel)
        homeViewModel.fetchProductsByJetpack()

        val navigator = LocalNavigator.currentOrThrow

        FloatingActionButton(
            onClick = {
                navigator.push(RegisterScreen)
            },
            modifier = Modifier
                .padding(end = 10.dp, bottom = 10.dp)
                .align(Alignment.BottomEnd),
            containerColor = colorResource(id = R.color.white)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "icon user"
            )
        }
    }
}
