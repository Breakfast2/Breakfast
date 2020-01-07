package com.example.breakfast.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.breakfast.R
import com.example.breakfast.model.FoodModel

class MainAdapter(data: List<FoodModel>?) :
    BaseQuickAdapter<FoodModel, BaseViewHolder>(R.layout.item_main, data) {
    override fun convert(helper: BaseViewHolder, item: FoodModel?) {
        helper.setText(R.id.tv_FoodName,item!!.foodName)
    }
}