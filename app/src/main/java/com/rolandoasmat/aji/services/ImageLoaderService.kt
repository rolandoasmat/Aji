package com.rolandoasmat.aji.services

import android.widget.ImageView

/**
 * Service for loading images
 */
interface ImageLoaderService {

    /**
     * Loads a URL into an image
     *
     * @param url URL to load
     * @param imageView ImageView to load the image into
     */
    fun load(url: String, imageView: ImageView)
}