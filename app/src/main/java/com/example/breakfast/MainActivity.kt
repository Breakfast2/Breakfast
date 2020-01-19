package com.example.breakfast

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.breakfast.adapter.DetailAdapter
import com.example.breakfast.adapter.MainAdapter
import com.example.breakfast.model.FoodModel
import com.example.breakfast.view.CustomerClickListener
import com.example.breakfast.view.CustomerRipple
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_detail.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var detailAdapter: DetailAdapter
    private val foodItemList = ArrayList<FoodModel>() //當前已點品項的List
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
                foodItemList.clear()
                detailAdapter.notifyDataSetChanged()
                totalMoney = 0
                tvTotalMoney.text = "總額 : $totalMoney 元"
            }
        })
    }

    private fun initAdapter() {
        detailRecycler.layoutManager = FlexboxLayoutManager(this)
        detailAdapter = DetailAdapter(foodItemList)
        detailAdapter.onItemClickListener = onDetailListItemClickListener
        detailAdapter.onItemLongClickListener = onDetailItemLongClickListener
        detailRecycler.adapter = detailAdapter

        mainRecycler.layoutManager = FlexboxLayoutManager(this)
        mainAdapter = MainAdapter(initMainList())
        mainAdapter.onItemClickListener = onMainListItemClickListener
        mainRecycler.adapter = mainAdapter

        emptyView()
    }

    private val onDetailListItemClickListener =
        BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val foodModel = adapter.data[position] as FoodModel
            toast(foodModel.foodName!! + " 有" + foodModel.foodCounter + " 份")
            emptyView()
        }

    private val onDetailItemLongClickListener =
        BaseQuickAdapter.OnItemLongClickListener { adapter, view, position ->
            val foodModel = adapter.data[position] as FoodModel
            toast(foodModel.foodName + "  刪除")
            overshootAnimation(view)
            true
        }

    private val onMainListItemClickListener =
        BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val foodModel = adapter.data[position] as FoodModel
            toast(foodModel.foodName!! + " $" + foodModel.foodPrice + " 元")
            foodModel.foodCounter = foodModel.foodCounter!! + 1
            addFoodItem(foodModel)
            detailAdapter.notifyDataSetChanged()
            detailRecycler.smoothScrollToPosition(detailAdapter.data.size)
            totalMoney += foodModel.foodPrice!!
            tvTotalMoney.text = "總額 : $totalMoney 元"
            emptyView()
        }

    //Menu
    private fun initMainList(): List<FoodModel> {
        val mainList = ArrayList<FoodModel>()
        mainList.add(FoodModel(true, "中西式餐點"))
        mainList.add(FoodModel(0, "中西式餐點", 0, "水煎包", 0, 8, 0))
        mainList.add(FoodModel(0, "中西式餐點", 1, "紅豆餅", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 2, "甜燒餅", 0, 10, 0))
        mainList.add(FoodModel(0, "中西式餐點", 3, "餡餅", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 4, "原味蛋餅", 0, 18, 0))
        mainList.add(FoodModel(0, "中西式餐點", 5, "玉米蛋餅", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 6, "培根蛋餅", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 7, "起司蛋餅", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 8, "蔥油餅", 0, 40, 0))
        mainList.add(FoodModel(0, "中西式餐點", 9, "肉鬆蛋餅", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 10, "火腿蛋餅", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 11, "肉粽", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 12, "盒裝壽司", 0, 30, 0))
        mainList.add(FoodModel(0, "中西式餐點", 13, "壽司", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 14, "飯糰", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 15, "熱狗捲", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 16, "燒餅", 0, 10, 0))
        mainList.add(FoodModel(0, "中西式餐點", 17, "油條", 0, 12, 0))
        mainList.add(FoodModel(0, "中西式餐點", 18, "果醬三明治", 0, 15, 0))
        mainList.add(FoodModel(0, "中西式餐點", 19, "夾蛋三明治", 0, 20, 0))
        mainList.add(FoodModel(0, "中西式餐點", 20, "蘿蔔糕", 0, 25, 0))
        mainList.add(FoodModel(0, "中西式餐點", 21, "荷包蛋", 0, 8, 0))
        mainList.add(FoodModel(0, "中西式餐點", 22, "蔥蛋", 0, 8, 0))
        mainList.add(FoodModel(0, "中西式餐點", 23, "茶葉蛋", 0, 8, 0))
        mainList.add(FoodModel(0, "中西式餐點", 24, "熱狗", 0, 5, 0))
        mainList.add(FoodModel(0, "中西式餐點", 25, "雞塊", 0, 20, 0))
        mainList.add(FoodModel(0, "中西式餐點", 26, "薯餅", 0, 20, 0))

        mainList.add(FoodModel(true, "冷熱飲"))
        mainList.add(FoodModel(1, "冷熱飲", 0, "豆漿", 0, 12, 0))
        mainList.add(FoodModel(1, "冷熱飲", 1, "米漿", 0, 12, 0))
        mainList.add(FoodModel(1, "冷熱飲", 2, "紅茶", 0, 12, 0))
        mainList.add(FoodModel(1, "冷熱飲", 3, "紅麴豆漿", 0, 15, 0))
        mainList.add(FoodModel(1, "冷熱飲", 4, "奶茶", 0, 20, 0))
        mainList.add(FoodModel(1, "冷熱飲", 5, "冷凍豆花", 0, 25, 0))

        mainList.add(FoodModel(true, "湯類"))
        mainList.add(FoodModel(2, "湯類", 0, "豬血湯", 0, 20, 0))
        mainList.add(FoodModel(2, "湯類", 1, "貢丸湯", 0, 20, 0))
        mainList.add(FoodModel(2, "湯類", 2, "酸辣湯", 0, 20, 0))
        mainList.add(FoodModel(2, "湯類", 3, "玉米濃湯", 0, 20, 0))
        mainList.add(FoodModel(2, "湯類", 4, "蛋花湯", 0, 15, 0))
        mainList.add(FoodModel(2, "湯類", 5, "皮蛋瘦肉粥", 0, 30, 0))
        mainList.add(FoodModel(2, "湯類", 6, "紫菜蛋花湯", 0, 20, 0))

        return mainList
    }

    //新增的項目
    private fun addFoodItem(foodModel: FoodModel): List<FoodModel> {
        val newFoodModel: FoodModel
        if (foodItemList.isEmpty()) {
            foodItemList.add(foodModel)
            return foodItemList
        }
        for (i in 0 until foodItemList.size) {
            if (foodItemList[i].foodCategorySeq == foodModel.foodCategorySeq && foodItemList[i].foodSeq == foodModel.foodSeq) {
                newFoodModel = FoodModel(
                    foodModel.foodCategorySeq,
                    foodModel.foodCategoryName,
                    foodModel.foodSeq,
                    foodModel.foodName,
                    foodModel.foodCounter,
                    foodModel.foodPrice,
                    foodModel.foodPrice!! * (foodModel.foodCounter!! + foodItemList[i].foodCounter!!)
                )
                foodItemList[i] = newFoodModel
                return foodItemList
            }
        }
        foodItemList.add(foodModel)
        return foodItemList
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

    private fun emptyView() {
        if (foodItemList.isEmpty()) {
            tvEmptyView.visibility = View.VISIBLE
            detailRecycler.visibility = View.GONE
        } else {
            tvEmptyView.visibility = View.GONE
            detailRecycler.visibility = View.VISIBLE
        }
    }

    private fun overshootAnimation(view:View){
        val animation = TranslateAnimation(5f,-5f,-5f,5f)
        animation.interpolator = OvershootInterpolator()
        animation.duration = 20
        animation.repeatCount = 5
        animation.repeatMode = Animation.REVERSE
        view.startAnimation(animation)
    }

    private fun toast(text: String) {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}
