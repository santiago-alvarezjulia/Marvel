package com.saj.marvel.ui.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PxToDP @Inject constructor(
    @ApplicationContext private val appContext: Context
) : PxToDpInt {
    override fun pxToDp(pxValue: Int) : Int {
        val density = appContext.resources.displayMetrics.density
        return (pxValue * density).toInt()
    }
}