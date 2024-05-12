package com.recipes.app.composeble

import androidx.compose.runtime.Composable
import com.recipes.domain.entity.RecipeModel

@Composable
fun RecipesCard(recipe: RecipeModel) {
    ItemProductOfCard(recipe)
}