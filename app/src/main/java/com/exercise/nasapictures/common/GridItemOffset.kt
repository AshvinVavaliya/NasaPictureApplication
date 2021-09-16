package com.exercise.nasapictures.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class GridItemOffset(private val offSet: Int) : RecyclerView.ItemDecoration() {
    constructor(@NonNull context: Context, @DimenRes itemOffset: Int) : this(context.resources.getDimensionPixelOffset(itemOffset))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(offSet, offSet, offSet, offSet)
    }
}