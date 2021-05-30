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
import com.jinal.mob.catalog.category.data.Category
import com.jinal.mob.catalog.utility.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productsList: RecyclerView
    private val categoryRepository = CategoryRepository()

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
            categoryRepository.getProducts(callback)
        } else {
            Log.e("CategoryFragment", "No Internet Connection")
//            AlertDialog.Builder(activity).setTitle("No Internet Connection")
//                .setMessage("Please check your internet connection and try again")
//                .setPositiveButton(android.R.string.ok) { _, _ -> }
//                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    private val callback = object : Callback<List<Category>> {
        override fun onFailure(call: Call<List<Category>>?, t: Throwable?) {
            Log.e("CategoryFragment", "Error {${t?.message}}")
        }

        override fun onResponse(call: Call<List<Category>>?, response: Response<List<Category>>?) {
            response?.isSuccessful.let {
                val catalogData = CatalogData(response?.body() ?: emptyList())
                productsList.adapter = ProductsListAdapter(catalogData)
            }
        }
    }
}