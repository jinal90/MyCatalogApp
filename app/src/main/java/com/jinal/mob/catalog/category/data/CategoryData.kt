package com.jinal.mob.catalog.category.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.core.app.NotificationCompat
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */

@Keep
data class Category(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("products") val products: List<Product>?
)

@Keep
data class Product(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("categoryId") val categoryId: String?,
    @SerializedName("salePrice") val salePrice: SalePrice?
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

@Keep
data class SalePrice(
    @SerializedName("amount") val amount: String?,
    @SerializedName("currency") val currency: String?
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