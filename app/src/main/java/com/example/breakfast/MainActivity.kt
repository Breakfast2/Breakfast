package com.example.breakfast

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.breakfast.adapter.DetailAdapter
import com.example.breakfast.adapter.MainAdapter
import com.example.breakfast.model.FoodModel
import com.example.breakfast.view.CustomerClickListener
import com.example.breakfast.view.CustomerRipple
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var detailAdapter: DetailAdapter
    private var detailList = ArrayList<FoodModel>()
    private var totalMoney = 0

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
        //detailAdapter
        detailRecycler.layoutManager = GridLayoutManager(this, 4)
        detailAdapter = DetailAdapter(detailList)
        detailAdapter.onItemClickListener = onDetailListItemClickListener
        detailRecycler.adapter = detailAdapter

        //MainAdapter
        mainRecycler.layoutManager = GridLayoutManager(this, 6)
        mainAdapter = MainAdapter(initMainList())
        mainAdapter.onItemClickListener = onMainListItemClickListener
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

    private val onDetailListItemClickListener =
        BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val foodModel = adapter.data[position] as FoodModel
            toast(foodModel.foodName!! + " 有" + foodModel.foodCounter + " 份")
        }

    private val onMainListItemClickListener =
        BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val foodModel = adapter.data[position] as FoodModel
            toast(foodModel.foodName!! + " $" + foodModel.foodMoney + " 元")
            detailList.add(
                FoodModel(
                    foodModel.foodName,
                    foodModel.foodCounter,
                    foodModel.foodMoney,
                    foodModel.foodCategory
                )
            )
            detailAdapter.notifyDataSetChanged()
            detailRecycler.smoothScrollToPosition(adapter.data.size)
            totalMoney += foodModel.foodMoney!!
            tvTotalMoney.text = "總額 : " + totalMoney + " 元"

        }

    //測試資料
    private fun initDetailList(): List<FoodModel> {
        val detailList = ArrayList<FoodModel>()
        detailList.add(FoodModel("漢堡", 1, 40, 1))
        detailList.add(FoodModel("豆漿", 1, 20, 2))
        detailList.add(FoodModel("牛排", 1, 80, 1))
        detailList.add(FoodModel("蛋餅", 1, 35, 1))
        detailList.add(FoodModel("紅茶", 10, 20, 2))
        detailList.add(FoodModel("漢堡", 1, 40, 1))
        detailList.add(FoodModel("豆漿", 1, 20, 2))
        detailList.add(FoodModel("牛排", 1, 80, 1))
        detailList.add(FoodModel("蛋餅", 1, 35, 1))
        detailList.add(FoodModel("紅茶", 10, 20, 2))
        detailList.add(FoodModel("漢堡", 1, 40, 1))
        detailList.add(FoodModel("豆漿", 1, 20, 2))
        detailList.add(FoodModel("牛排", 1, 80, 1))
        detailList.add(FoodModel("蛋餅", 1, 35, 1))
        detailList.add(FoodModel("紅茶", 10, 20, 2))
        return detailList
    }

    private fun initMainList(): List<FoodModel> {
        val mainList = ArrayList<FoodModel>()
        mainList.add(FoodModel("漢堡", 1, 40, 1))
        mainList.add(FoodModel("吐司", 1, 40, 1))
        mainList.add(FoodModel("蛋", 1, 40, 1))
        mainList.add(FoodModel("熱狗", 1, 40, 1))
        mainList.add(FoodModel("紅茶", 1, 40, 1))
        mainList.add(FoodModel("綠茶", 1, 40, 1))
        mainList.add(FoodModel("薯餅", 1, 40, 1))
        mainList.add(FoodModel("柳葉條", 1, 40, 1))
        mainList.add(FoodModel("豆漿", 1, 40, 1))
        mainList.add(FoodModel("包子", 1, 40, 1))
        mainList.add(FoodModel("叉燒包", 1, 40, 1))
        mainList.add(FoodModel("水煎包", 1, 40, 1))
        mainList.add(FoodModel("鬆餅", 1, 40, 1))
        mainList.add(FoodModel("飯糰", 1, 40, 1))
        mainList.add(FoodModel("蚵ㄚ麵線", 1, 40, 1))
        mainList.add(FoodModel("雞排", 1, 40, 1))
        mainList.add(FoodModel("炒麵", 1, 40, 1))
        mainList.add(FoodModel("漢堡", 1, 40, 1))
        mainList.add(FoodModel("吐司", 1, 40, 1))
        mainList.add(FoodModel("蛋", 1, 10, 1))
        mainList.add(FoodModel("熱狗", 1, 10, 1))
        mainList.add(FoodModel("紅茶", 1, 20, 1))
        mainList.add(FoodModel("綠茶", 1, 20, 1))
        mainList.add(FoodModel("薯餅", 1, 20, 1))
        mainList.add(FoodModel("柳葉條", 1, 10, 1))
        mainList.add(FoodModel("豆漿", 1, 10, 1))
        mainList.add(FoodModel("包子", 1, 20, 1))
        mainList.add(FoodModel("叉燒包", 1, 20, 1))
        mainList.add(FoodModel("水煎包", 1, 10, 1))
        mainList.add(FoodModel("鬆餅", 1, 20, 1))
        mainList.add(FoodModel("飯糰", 1, 30, 1))
        mainList.add(FoodModel("蚵ㄚ麵線", 1, 50, 1))
        mainList.add(FoodModel("雞排", 1, 60, 1))
        mainList.add(FoodModel("炒麵", 1, 40, 1))
        mainList.add(FoodModel("漢堡", 1, 40, 1))
        mainList.add(FoodModel("吐司", 1, 20, 1))
        mainList.add(FoodModel("蛋", 1, 10, 1))
        mainList.add(FoodModel("熱狗", 1, 30, 1))
        mainList.add(FoodModel("紅茶", 1, 20, 1))
        mainList.add(FoodModel("綠茶", 1, 20, 1))
        mainList.add(FoodModel("薯餅", 1, 30, 1))
        mainList.add(FoodModel("柳葉條", 1, 35, 1))
        mainList.add(FoodModel("豆漿", 1, 10, 1))
        mainList.add(FoodModel("包子", 1, 20, 1))
        mainList.add(FoodModel("叉燒包", 1, 35, 1))
        mainList.add(FoodModel("水煎包", 1, 20, 1))
        mainList.add(FoodModel("鬆餅", 1, 100, 1))
        mainList.add(FoodModel("飯糰", 1, 40, 1))
        mainList.add(FoodModel("蚵ㄚ麵線", 1, 35, 1))
        mainList.add(FoodModel("雞排", 1, 40, 1))
        mainList.add(FoodModel("炒麵", 1, 35, 1))
        mainList.add(FoodModel("漢堡", 1, 40, 1))
        mainList.add(FoodModel("吐司", 1, 35, 1))
        mainList.add(FoodModel("蛋", 1, 10, 1))
        mainList.add(FoodModel("熱狗", 1, 20, 1))
        mainList.add(FoodModel("紅茶", 1, 20, 1))
        mainList.add(FoodModel("綠茶", 1, 20, 1))
        mainList.add(FoodModel("薯餅", 1, 35, 1))
        mainList.add(FoodModel("柳葉條", 1, 30, 1))
        mainList.add(FoodModel("豆漿", 1, 20, 1))
        mainList.add(FoodModel("包子", 1, 25, 1))
        mainList.add(FoodModel("叉燒包", 1, 25, 1))
        mainList.add(FoodModel("水煎包", 1, 20, 1))
        mainList.add(FoodModel("鬆餅", 1, 70, 1))
        mainList.add(FoodModel("飯糰", 1, 35, 1))
        mainList.add(FoodModel("蚵ㄚ麵線", 1, 40, 1))
        mainList.add(FoodModel("雞排", 1, 60, 1))
        mainList.add(FoodModel("炒麵", 1, 50, 1))
        return mainList
    }
}
