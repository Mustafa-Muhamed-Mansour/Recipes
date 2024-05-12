package com.recipes.domain.repository

import com.recipes.app.utils.Resource
import com.recipes.domain.response.RecipesResponse

interface HomeRepository {
    suspend fun fetchRecipesByXML(limit: Int): RecipesResponse
    suspend fun fetchRecipesByJetpack(): Resource<RecipesResponse?>

}