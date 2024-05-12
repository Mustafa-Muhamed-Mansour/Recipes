package com.recipes.domain.response

import com.google.gson.annotations.SerializedName
import com.recipes.domain.entity.RecipeModel

data class RecipesResponse(
    @SerializedName("recipes")
    val recipeModel: ArrayList<RecipeModel>
)