package com.rolandoasmat.aji.services

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageLoader : ImageLoaderService {

    override fun load(url: String, imageView: ImageView) {
         Picasso
             .get()
             .load(url)
             .resize(600, 400)
             .into(imageView)
    }
}