package com.recipes.app.utils

import com.recipes.domain.entity.RecipeModel


data class MainState(
    val isLoading: Boolean = false,
    val data: List<RecipeModel> = emptyList(),
    val error: String = ""
)
