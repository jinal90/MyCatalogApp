package com.jinal.mob.catalog.category.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.category.api.CategoryRepository
import com.jinal.mob.catalog.category.data.CatalogData
import com.jinal.mob.catalog.utility.Utils
import kotlinx.coroutines.*

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        productsList = root.findViewById(R.id.recycler_view_items)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Utils().isNetworkConnected(activity)) {
            //TODO: move service call to main activity instead of fragment
            val mainActivityJob = Job()
            val errorHandler = CoroutineExceptionHandler { _, exception ->
                //TODO: handle error
                Log.e("CategoryFragment", "Error ${exception.message}")
            }

            val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
            coroutineScope.launch(errorHandler) {
                val resultList = CategoryRepository().getProducts()
                val catalogData = CatalogData(resultList)
                productsList.adapter = ProductsListAdapter(catalogData)
            }
        } else {
            //TODO: Handle no internet connection
            Log.e("CategoryFragment", "No Internet Connection")
        }
    }
}