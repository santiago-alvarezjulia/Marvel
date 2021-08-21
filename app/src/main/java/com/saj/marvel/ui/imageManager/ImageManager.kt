package com.saj.marvel.ui.imageManager

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment

interface ImageManager {
    fun loadImage(context: Context, imageView: ImageView, imageUrl: String)
    fun loadImage(fragment: Fragment, imageView: ImageView, imageUrl: String)
}