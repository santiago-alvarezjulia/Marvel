package com.saj.marvel.ui.imageManager

import android.content.Context
import android.widget.ImageView

interface ImageManager {
    fun loadImage(context: Context, imageView: ImageView, imageUrl: String)
}