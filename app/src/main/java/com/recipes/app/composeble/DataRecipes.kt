package com.recipes.app.composeble

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.recipes.app.home.viewmodel.HomeViewModel

@Composable
fun DataRecipes(homeViewModel: HomeViewModel) {

    LazyColumn(
        modifier = Modifier.wrapContentSize(),
    ) {
        homeViewModel.listRecipes.value.data?.let {
            items(it) { recipes ->
                RecipesCard(recipe = recipes)
            }
        }
    }
}