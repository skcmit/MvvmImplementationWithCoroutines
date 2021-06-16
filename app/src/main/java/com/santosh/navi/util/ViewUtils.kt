package com.santosh.navi.util

import android.view.View
import android.widget.TextView
import com.santosh.navi.R

fun TextView.setOptionalText(charSequence: CharSequence?, dependentView: TextView?= null) {
    this.visibility = View.VISIBLE
    dependentView?.visibility = View.VISIBLE
    if(charSequence.isNullOrEmpty()) {
        this.text = this.context.getString(R.string.not_available)
    } else {
        this.text = charSequence
    }
}