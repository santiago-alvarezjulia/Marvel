package com.saj.marvel.ui.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.ui.utils.PxToDP
import javax.inject.Inject

class CharactersItemDecoration @Inject constructor(
    private val pxToDP: PxToDP
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spaceSize = pxToDP.pxToDp(8)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}
