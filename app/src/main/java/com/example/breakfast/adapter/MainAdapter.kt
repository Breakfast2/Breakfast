package com.example.breakfast.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.breakfast.R
import com.example.breakfast.model.FoodModel

class MainAdapter(data: List<FoodModel>?) :
    BaseQuickAdapter<FoodModel, BaseViewHolder>(R.layout.item_main, data) {
    override fun convert(helper: BaseViewHolder, item: FoodModel?) {

        if (item!!.title) {
            helper.setText(R.id.tv_title, item.foodCategoryName + "                                                                                          ")
        } else {
            helper.setText(R.id.tv_FoodName, item.foodName)
        }
        Log.d("123", "" + item.title)


        helper.setGone(R.id.cl_Title, item.title)
        helper.setGone(R.id.tv_FoodName, !item.title)
        helper.setEnabled(R.id.cl, !item.title)
    }
}