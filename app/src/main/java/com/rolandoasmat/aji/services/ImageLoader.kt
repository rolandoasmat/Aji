package com.rolandoasmat.aji.services

import android.widget.ImageView

object ImageLoader : ImageLoaderService {

    override fun load(url: String, imageView: ImageView) {
        // Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }
}