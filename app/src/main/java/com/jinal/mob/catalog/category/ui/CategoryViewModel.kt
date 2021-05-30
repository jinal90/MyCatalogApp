package com.jinal.mob.catalog.category.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * @author Jinal Tandel
 * @since 30/05/2021
 */
class CategoryViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Category Fragment"
    }

    val text: LiveData<String> = _text
}