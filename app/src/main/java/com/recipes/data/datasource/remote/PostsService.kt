package com.recipes.data.datasource.remote

import com.recipes.app.utils.Constants.END_POINT
import com.recipes.domain.response.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET(END_POINT)
    suspend fun fetchRecipesByXML(
        @Query("limit") limit: Int
    ): RecipesResponse


    @GET(END_POINT)
    suspend fun fetchRecipesByJetpack(): RecipesResponse?

}
