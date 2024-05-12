package com.recipes.app.composeble

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.recipes.app.home.viewmodel.HomeViewModel
import com.recipes.R

@Composable
fun CheckDataOfHome(homeViewModel: HomeViewModel) {

    val resultList = homeViewModel.listRecipes.value

    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        if (resultList.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.black)
                )
            }
        }
        if (resultList.error.isNotBlank()) {
            Text(
                text = resultList.error,
                color = colorResource(id = R.color.black),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        if (resultList.data.isNotEmpty()) {
            DataRecipes(homeViewModel = homeViewModel)
        }
    }
}