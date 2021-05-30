package com.jinal.mob.catalog.category.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.category.data.Product
import com.jinal.mob.catalog.databinding.FragmentCategoryBinding
import com.jinal.mob.catalog.utility.Utils

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
open class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var productsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_category,
            container,
            false
        )

        categoryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        binding.lifecycleOwner = this
        binding.categoryViewModel = categoryViewModel

        productsList = binding.recyclerViewItems


        return binding.root
    }

    fun getProducts(categoryID: Int) {
        if (Utils().isNetworkConnected(activity)) {
            categoryViewModel.getCategoryList().observe(viewLifecycleOwner, Observer {
                productsList.adapter = ProductsListAdapter(it[categoryID]) { product: Product ->
                    categoryViewModel.setSelectedProduct(product)


                    if (product.categoryId == "36802") {
                        val action =
                            FoodFragmentDirections.actionNavigationFoodToProductFragment(product)
                        findNavController(this).navigate(action)
                    } else {
                        val action =
                            BeverageFragmentDirections.actionNavigationBeveragesToProductFragment(
                                product
                            )
                        findNavController(this).navigate(action)
                    }
                }
            })

        } else {
            //TODO: Handle no internet connection
            Log.e("CategoryFragment", "No Internet Connection")
        }
    }

}