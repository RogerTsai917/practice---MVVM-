package com.rogertsai.mymvvm.ui

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("visibleGone")
fun View.showHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(url: String) {
    Glide.with(this.context)
            .load(url)
            .into(this)
}
