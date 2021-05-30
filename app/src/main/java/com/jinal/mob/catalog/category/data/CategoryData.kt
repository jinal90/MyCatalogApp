package com.jinal.mob.catalog.category.data

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(SalePrice::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(categoryId)
        parcel.writeParcelable(salePrice, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}

data class SalePrice(
    val amount: String?,
    val currency: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(amount)
        parcel.writeString(currency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SalePrice> {
        override fun createFromParcel(parcel: Parcel): SalePrice {
            return SalePrice(parcel)
        }

        override fun newArray(size: Int): Array<SalePrice?> {
            return arrayOfNulls(size)
        }
    }
}