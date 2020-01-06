package com.example.breakfast.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.breakfast.R

class DetailAdapter(data: List<*>?) :
    BaseQuickAdapter<Any, BaseViewHolder>(R.layout.item_main, data) {
    override fun convert(helper: BaseViewHolder, item: Any?) {


    }
}