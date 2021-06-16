package com.santosh.navi.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrolledToEnd(function: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        private val VISIBLE_ITEM_THRESHOLD = 2
        private var isLoading = true
        private var previousTotal = 0

        override fun onScrollStateChanged(
            recyclerView: RecyclerView,
            newState: Int
        ) {
            with(layoutManager as LinearLayoutManager) {
                val visibleItemCount = childCount
                val totalItemCount = itemCount
                val firstVisibleItem = findFirstVisibleItemPosition()
                if (isLoading && totalItemCount > previousTotal) {
                    isLoading = false
                    previousTotal = totalItemCount
                }
                if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_ITEM_THRESHOLD)) {
                    function()
                    isLoading = true
                }
            }
        }
    })
}