package com.jinal.mob.catalog.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.category.api.RetrofitInstance
import com.jinal.mob.catalog.databinding.FragmentProductBinding
import com.squareup.picasso.Picasso

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
open class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    lateinit var productViewModel: CategoryViewModel

    val args: ProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
            container,
            false
        )

        productViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvProductName.text = args.product?.name
        binding.tvProductDescription.text = args.product?.description
        binding.tvProductPrice.text =
            args.product?.salePrice?.amount + " " + args.product?.salePrice?.currency
        Picasso.get().load(RetrofitInstance.BASE_URL + args.product?.url)
            .into(binding.ivProductImage)

    }
}