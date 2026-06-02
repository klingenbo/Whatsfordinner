package com.example.whatsfordinner.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlin.getValue
import kotlin.jvm.java

object RetrofitInstance {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: RecipeApi by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipeApi::class.java)
    }
}

interface RecipeApi {
    @GET("search.php?f=a")
    suspend fun getRecipes(): RecipeResponseDto
}