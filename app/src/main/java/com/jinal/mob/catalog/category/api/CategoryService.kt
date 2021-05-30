package com.jinal.mob.catalog.category.api

import com.jinal.mob.catalog.category.data.Category

import retrofit2.http.GET

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
interface CategoryService {

    @GET(".")
    suspend fun retrieveProducts(): List<Category>
}