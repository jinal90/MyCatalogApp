package com.jinal.mob.catalog.category.api

import com.jinal.mob.catalog.category.data.Category
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class RetrofitInstance {
    private val service: CategoryService

    companion object {
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(CategoryService::class.java)
    }

    suspend fun getProducts(): List<Category> {
        return service.retrieveProducts()
    }

}