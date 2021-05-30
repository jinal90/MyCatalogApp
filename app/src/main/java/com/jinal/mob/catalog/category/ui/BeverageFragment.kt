package com.jinal.mob.catalog.category.ui

import android.os.Bundle
import android.view.View

/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class BeverageFragment : CategoryFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts(1)
    }
}