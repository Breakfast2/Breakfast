package com.example.breakfast

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakfast.adapter.DetailAdapter
import com.example.breakfast.adapter.MainAdapter
import com.example.breakfast.view.CustomerClickListener
import com.example.breakfast.view.CustomerRipple
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var mainList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentStatusBar()
        initView()
        initClick()
        initAdapter()
    }

    private fun initView() {
        val customerRipple = CustomerRipple(ContextCompat.getColor(this, R.color.green_45C2C5), 50)
        tvNext1.background = customerRipple.rippleDrawable()
        tvNext2.background = customerRipple.rippleDrawable()
        tvStoreName.text = "明昇早餐店"
    }

    private fun initClick() {
        tvNext1!!.setOnClickListener(object : CustomerClickListener() {
            override fun onOneClick() {
                toast("你點到我拉")
            }
        })

        tvNext2!!.setOnClickListener(object : CustomerClickListener() {
            override fun onOneClick() {
                toast("你又點到我拉")
            }
        })
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this)
        //detailAdapter
        detailRecycler.layoutManager = layoutManager
        detailAdapter = DetailAdapter(mainList)
        detailRecycler.adapter = detailAdapter

        //MainAdapter
        mainRecycler.layoutManager = layoutManager
        mainAdapter = MainAdapter(mainList)
        mainRecycler.adapter = mainAdapter
    }

    private fun transparentStatusBar() {
        //透明狀態欄
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        tvStoreName.setPadding(
            tvStoreName.paddingLeft,
            tvStoreName.paddingTop + getStatusBarHeight() + 10,
            tvStoreName.paddingRight,
            tvStoreName.paddingBottom
        )
    }

    private fun getStatusBarHeight(): Int {
        var result = 24
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        result = if (resId > 0) {
            resources.getDimensionPixelSize(resId)
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                result.toFloat(), Resources.getSystem().displayMetrics
            ).toInt()
        }
        return result
    }

    private fun toast(text: String) {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}
