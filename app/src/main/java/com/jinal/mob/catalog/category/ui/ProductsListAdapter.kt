package com.jinal.mob.catalog.category.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.R
import com.jinal.mob.catalog.category.data.Category
import com.jinal.mob.catalog.category.data.Product

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class ProductsListAdapter(private val category: Category) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        category.products?.get(position)?.let { holder.bindRepo(it) }
    }

    override fun getItemCount(): Int = category.products?.size ?: 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(product: Product) {
            //TODO: set visibility according to data available
            itemView.findViewById<TextView>(R.id.tv_item_name).text = product.name.orEmpty()
            itemView.findViewById<TextView>(R.id.tv_item_description).text =
                product.description.orEmpty()
            itemView.findViewById<TextView>(R.id.tv_item_description).visibility = View.GONE
            itemView.findViewById<TextView>(R.id.tv_item_price).text = "NA"
            product.salePrice?.amount?.let {
                itemView.findViewById<TextView>(R.id.tv_item_price).text =
                    product.salePrice?.currency + " " + it
            }
        }
    }
}