package com.jinal.mob.catalog.category.data

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
data class CatalogData(val items: List<Category>)

data class Category(
    val id: String?,
    val name: String?,
    val description: String?,
    val products: List<Product>?
)

data class Product(
    val id: String?,
    val name: String?,
    val url: String?,
    val description: String?,
    val categoryId: String?,
    val salePrice: SalePrice?
)

data class SalePrice(
    val amount: String?,
    val currency: String?
)