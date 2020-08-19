package com.rolandoasmat.aji.extensions

import android.view.View
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun AppBarLayout.setOnCollapsedListener(listener: (Boolean) -> Unit) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        appBarLayout?.let { appBar ->
            val isCollapsed = abs(verticalOffset)  == appBar.totalScrollRange
            listener(isCollapsed)
        }
    })
}