package com.jinal.mob.catalog.product.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.databinding.FragmentCategoryBinding
import com.jinal.mob.catalog.utility.Utils

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
open class ProductFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    lateinit var productViewModel: ProductViewModel

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

        productViewModel =
            ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.lifecycleOwner = this




        return binding.root
    }
}