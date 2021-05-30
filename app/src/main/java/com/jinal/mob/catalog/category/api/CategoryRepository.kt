package com.jinal.mob.catalog.category.api

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    private var context: Context? = null

    fun setContext(ctx: Context?){
        context = ctx
    }
    private val _category = MutableLiveData<List<Category>>().apply {
        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Toast.makeText(context, "Error ${exception.message}", Toast.LENGTH_LONG).show()
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