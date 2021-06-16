package com.santosh.navi.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> Fragment.getViewModel(
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return if (viewModelFactory == null) {
        ViewModelProvider(this).get(viewModelClass)
    } else {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }
}