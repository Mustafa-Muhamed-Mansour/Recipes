package com.recipes.domain.usecase

import com.recipes.domain.repository.HomeRepository

class FetchRecipes(private val homeRepository: HomeRepository) {
    suspend fun fetchRecipesByUsingXMLUseCase(limit: Int) = homeRepository.fetchRecipesByXML(limit = limit)
    suspend fun fetchRecipesByUsingJetpackUseCase() = homeRepository.fetchRecipesByJetpack()
}