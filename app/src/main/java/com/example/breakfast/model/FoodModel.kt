package com.example.breakfast.model

class FoodModel {
    var foodName: String? = null
    var foodCounter: Int? = null
    var foodMoney: Int? = null
    var foodCategory: Int? = null

    constructor(foodName: String?, foodCounter: Int?, foodMoney: Int?, foodCategory: Int?) {
        this.foodName = foodName
        this.foodCounter = foodCounter
        this.foodMoney = foodMoney
        this.foodCategory = foodCategory
    }
}