package com.saj.marvel.ui.imageManager

import android.content.Context
import android.widget.ImageView
import javax.inject.Inject

class GlideManager @Inject constructor(): ImageManager {
    override fun loadImage(context: Context, imageView: ImageView, imageUrl: String) {
        GlideApp.with(context)
            .load(imageUrl)
            .into(imageView)
    }
}