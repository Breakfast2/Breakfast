package com.example.breakfast.view

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region
import android.view.ViewTreeObserver

import androidx.recyclerview.widget.RecyclerView

class RecyclerViewCornerRadius(recyclerView: RecyclerView) {

    private var rectF: RectF? = null
    private var path: Path? = null

    private var topLeftRadius = 0
    private var topRightRadius = 0
    private var bottomLeftRadius = 0
    private var bottomRightRadius = 0

    init {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rectF = RectF(
                    0f,
                    0f,
                    recyclerView.measuredWidth.toFloat(),
                    recyclerView.measuredHeight.toFloat()
                )

                path = Path()
                path!!.reset()
                path!!.addRoundRect(
                    rectF!!,
                    floatArrayOf(
                        topLeftRadius.toFloat(),
                        topLeftRadius.toFloat(),
                        topRightRadius.toFloat(),
                        topRightRadius.toFloat(),
                        bottomLeftRadius.toFloat(),
                        bottomLeftRadius.toFloat(),
                        bottomRightRadius.toFloat(),
                        bottomRightRadius.toFloat()
                    ),
                    Path.Direction.CCW
                )

                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    fun setCornerRadius(radius: Int) {
        this.topLeftRadius = radius
        this.topRightRadius = radius
        this.bottomLeftRadius = radius
        this.bottomRightRadius = radius
    }

    fun setCornerRadius(
        topLeftRadius: Int,
        topRightRadius: Int,
        bottomLeftRadius: Int,
        bottomRightRadius: Int
    ) {
        this.topLeftRadius = topLeftRadius
        this.topRightRadius = topRightRadius
        this.bottomLeftRadius = bottomLeftRadius
        this.bottomRightRadius = bottomRightRadius
    }

    fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.clipRect(rectF!!)
        c.clipPath(path!!, Region.Op.REPLACE)
    }

    companion object {
        val TAG = "RecyclerViewCornerRadius"
    }
}
