package com.jinal.mob.catalog.category.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jinal.mob.catalog.category.api.CategoryRepository
import com.jinal.mob.catalog.category.data.Category


/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryViewModel : ViewModel() {

    private val categoryRepository: CategoryRepository = CategoryRepository()


    fun getCategoryList(): LiveData<List<Category>> {
        return categoryRepository.categoryList
    }
}