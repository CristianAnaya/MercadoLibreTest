package com.redwork.mercadolibre.framework.imagemanager

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun ImageView.bindImageUrl(url: String?, @DrawableRes placeholder: Int,
                           @DrawableRes errorPlaceholder: Int) {
    if (url.isNullOrBlank()) {
        setImageResource(placeholder)
        return
    }

    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(errorPlaceholder)
        .placeholder(placeholder)
        .into(this)
}