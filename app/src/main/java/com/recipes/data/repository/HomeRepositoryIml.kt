package com.recipes.data.repository

import com.recipes.app.utils.Resource
import com.recipes.data.datasource.remote.PostsService
import com.recipes.domain.repository.HomeRepository
import com.recipes.domain.response.RecipesResponse
import org.koin.core.component.KoinComponent

class HomeRepositoryIml(
    private val postsService: PostsService
) : HomeRepository, KoinComponent {
    override suspend fun fetchRecipesByXML(limit: Int) = postsService.fetchRecipesByXML(limit = limit)
//    override suspend fun fetchRecipesByXML(limit: Int) = postsService.fetchRecipesByXML(limit = limit)

    override suspend fun fetchRecipesByJetpack(): Resource<RecipesResponse?> {
        return try {
            val result = postsService.fetchRecipesByJetpack()
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}