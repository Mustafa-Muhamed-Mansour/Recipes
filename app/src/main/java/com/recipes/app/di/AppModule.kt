package com.recipes.app.di

import com.recipes.app.home.viewmodel.HomeViewModel
import com.recipes.app.register.viewmodel.RegisterViewModel
import com.recipes.app.utils.Constants
import com.recipes.data.datasource.remote.PostsService
import com.recipes.data.repository.HomeRepositoryIml
import com.recipes.data.repository.RegisterRepositoryIml
import com.recipes.domain.repository.HomeRepository
import com.recipes.domain.repository.RegisterRepository
import com.recipes.domain.usecase.FetchRecipes
import com.recipes.domain.usecase.RegisterAccountUseCase
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


val viewModelModule = module {
    single { HomeViewModel(fetchRecipes = get()) }
    single { RegisterViewModel(registerAccountUseCase = get()) }
}

val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryIml(postsService = get()) }
    single<RegisterRepository> { RegisterRepositoryIml(context = androidContext()) }
}

val useCaseModule = module {
    single<FetchRecipes> { FetchRecipes(homeRepository = get()) }
    single<RegisterAccountUseCase> { RegisterAccountUseCase(registerRepository = get()) }
}

val networkModule = module {
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
    single { provideOkHttp() }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { provideRetrofit(okHttpClient = get()) }

    fun getAPIPosts(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }
    single { getAPIPosts(retrofit = get()) }
}