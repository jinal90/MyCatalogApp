package com.jinal.mob.catalog.category.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jinal.mob.catalog.category.api.RetrofitInstance
import com.jinal.mob.catalog.category.data.Category
import com.jinal.mob.catalog.category.data.Product
import com.jinal.mob.catalog.databinding.ProductItemRowBinding
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
        holder.binding.tvItemPrice.text = category.products?.get(position)?.salePrice?.amount +
                " " + category.products?.get(position)?.salePrice?.currency
        Picasso.get().load(RetrofitInstance.BASE_URL + category.products?.get(position)?.url)
            .into(holder.binding.ivItemImage)

        holder.binding.cardView.setOnClickListener {
            listener(category.products?.get(position))
        }

    }

    override fun getItemCount(): Int = category.products?.size ?: 0


    inner class ViewHolder(val binding: ProductItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)
//    {
//        var navController: NavController? = null
//        fun bindRepo(product: Product, listener: (Product) -> Unit) {
//            //TODO: set visibility according to data available
//            binding{
//
//            }
//            binding.tv_item_name.text = product.name.orEmpty()
//            itemView.findViewById<TextView>(R.id.tv_item_description).text =
//                product.description.orEmpty()
//            itemView.findViewById<TextView>(R.id.tv_item_description).visibility = View.GONE
//            itemView.findViewById<TextView>(R.id.tv_item_price).text = "NA"
//            product.salePrice?.amount?.let {
//                itemView.findViewById<TextView>(R.id.tv_item_price).text =
//                    product.salePrice?.currency + " " + it
//            }
//            Picasso.get().load(RetrofitInstance.BASE_URL + product.url)
//                .into(itemView.findViewById<ImageView>(R.id.iv_item_image))
//
//            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener {
//                navController = Navigation.findNavController(itemView)
//                listener(product)
//            }
//
//        }
//    }
}



