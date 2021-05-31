package com.jinal.mob.catalog.category.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.category.api.RetrofitInstance
import com.jinal.mob.catalog.category.data.Category
import com.jinal.mob.catalog.category.data.Product
import com.jinal.mob.catalog.databinding.ProductItemRowBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class ProductsListAdapter(private val category: Category, val listener: (Product?) -> Unit) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItemName.text = category.products?.get(position)?.name
        holder.binding.tvItemDescription.text = category.products?.get(position)?.description
        holder.binding.tvItemPrice.text = "${category.products?.get(position)?.salePrice?.amount} ${category.products?.get(position)?.salePrice?.currency}"

        category.products?.get(position)?.url?.let {
            Picasso.get().load(RetrofitInstance.BASE_URL + it)
                .into(holder.binding.ivItemImage, object: Callback {
                    override fun onSuccess() {
                    }
                    override fun onError(e: java.lang.Exception?) {
                        holder.binding.ivItemImage.setImageResource(R.drawable.ic_base_image_100)
                    }
                })
        }
        holder.binding.cardView.setOnClickListener {
            listener(category.products?.get(position))
        }

    }

    override fun getItemCount(): Int = category.products?.size ?: 0


    inner class ViewHolder(val binding: ProductItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}



