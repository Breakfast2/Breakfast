package com.example.breakfast.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.breakfast.R
import com.example.breakfast.model.FoodModel

class DetailAdapter(data: List<FoodModel>?) :
    BaseQuickAdapter<FoodModel, BaseViewHolder>(R.layout.item_detail, data) {
    override fun convert(helper: BaseViewHolder, item: FoodModel?) {
        helper.setText(R.id.tv_Name,item?.foodName)
        helper.setText(R.id.tv_Counter,"" + item?.foodCounter)
    }
}