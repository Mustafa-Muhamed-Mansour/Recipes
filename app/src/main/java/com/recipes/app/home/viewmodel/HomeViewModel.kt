package com.recipes.app.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipes.app.utils.Constants
import com.recipes.app.utils.MainState
import com.recipes.app.utils.Resource
import com.recipes.domain.response.RecipesResponse
import com.recipes.domain.usecase.FetchRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val fetchRecipes: FetchRecipes
) : ViewModel(), KoinComponent {


    private val _recipes: MutableStateFlow<RecipesResponse?> = MutableStateFlow(null)
    val recipes: StateFlow<RecipesResponse?> get() = _recipes

    private val _listRecipes: MutableState<MainState> = mutableStateOf(MainState())
    val listRecipes get() = _listRecipes

    private val _error: MutableStateFlow<String> = MutableStateFlow("")
    val error: StateFlow<String> get() = _error

    init {
        _listRecipes.value = MainState(isLoading = true)
    }

    fun fetchPostsByXML() {
        viewModelScope.launch {
            try {
                _recipes.value =
                    fetchRecipes.fetchRecipesByUsingXMLUseCase(limit = Constants.LIMIT)
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    fun fetchProductsByJetpack() {
        viewModelScope.launch {
            try {
                when (val products = fetchRecipes.fetchRecipesByUsingJetpackUseCase()) {
                    is Resource.Error -> {
                        _listRecipes.value = MainState(error = "Something went wrong")
                    }

                    is Resource.Success -> {
                        products.data?.let {
                            _listRecipes.value = MainState(data = it.recipeModel.toList())
                        }
                    }

                    is Resource.Loading -> {
                        _listRecipes.value = MainState(isLoading = true)
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}