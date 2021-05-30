package com.jinal.mob.catalog.category.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jinal.mob.catalog.category.api.CategoryRepository
import com.jinal.mob.catalog.category.data.Category
import com.jinal.mob.catalog.category.data.Product


/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryViewModel : ViewModel() {

    private val categoryRepository: CategoryRepository = CategoryRepository()

    fun getCategoryList(context: Context?): LiveData<List<Category>> {
        categoryRepository.setContext(context)
        return categoryRepository.categoryList
    }

    private val _selectedProduct = MutableLiveData<Product>().apply {
        value = p
    }
    var selectedProduct: LiveData<Product>? = null

    var p: Product? = null
    fun setSelectedProduct(product: Product?) {
        selectedProduct = _selectedProduct
    }
}