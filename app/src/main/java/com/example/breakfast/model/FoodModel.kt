package com.example.breakfast.model

class FoodModel{
    var title:Boolean = false
    var foodCategorySeq: Int?= null
    var foodCategoryName: String?= null
    var foodSeq: Int?= null
    var foodName: String?= null
    var foodCounter: Int?= null
    var foodPrice: Int?= null
    var foodTotalMoney:Int?= null

    constructor(
        foodCategorySeq: Int?,
        foodCategoryName: String?,
        foodSeq: Int?,
        foodName: String?,
        foodCounter: Int?,
        foodPrice: Int?,
        foodTotalMoney: Int?
    ) {
        this.foodCategorySeq = foodCategorySeq
        this.foodCategoryName = foodCategoryName
        this.foodSeq = foodSeq
        this.foodName = foodName
        this.foodCounter = foodCounter
        this.foodPrice = foodPrice
        this.foodTotalMoney = foodTotalMoney
    }

    constructor(title: Boolean, foodCategoryName: String?) {
        this.title = title
        this.foodCategoryName = foodCategoryName
    }
}








