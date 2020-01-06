package com.example.breakfast

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //透明狀態欄
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        tvStoreName.text = "明昇早餐店"
        tvStoreName.setPadding(tvStoreName.paddingLeft,tvStoreName.paddingTop + getStatusBarHeight(),tvStoreName.paddingRight,tvStoreName.paddingBottom)


    }

    private fun getStatusBarHeight(): Int {
        var result = 24
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        result = if (resId > 0) {
            resources.getDimensionPixelSize(resId)
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                result.toFloat(), Resources.getSystem().displayMetrics).toInt()
        }
        return result
    }
}
