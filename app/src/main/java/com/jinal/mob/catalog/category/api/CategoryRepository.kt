package com.jinal.mob.catalog.category.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jinal.mob.catalog.category.data.Category
import kotlinx.coroutines.*

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 *
 * Repository for Category list mutable live data
 */
class CategoryRepository {

    private val _category = MutableLiveData<List<Category>>().apply {
        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            //TODO: handle error
            Log.e("CategoryFragment", "Error ${exception.message}")
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            val resultList = RetrofitInstance().getProducts()
            value = resultList
        }
    }
    val categoryList: LiveData<List<Category>> = _category

}