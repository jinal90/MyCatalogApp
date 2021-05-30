package com.jinal.mob.catalog.category.api

import com.jinal.mob.catalog.category.data.Category
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryRepository {
    private val service: CategoryService

    companion object {
        //1
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/"
    }

    init {
        // 2
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL)
            //3
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //4
        service = retrofit.create(CategoryService::class.java)
    }

    fun getProducts(callback: Callback<List<Category>>) { //5
        val call = service.retrieveProducts()
        call.enqueue(callback)
    }
}