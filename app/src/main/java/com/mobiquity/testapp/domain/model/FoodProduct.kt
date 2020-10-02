package com.mobiquity.testapp.domain.model

class FoodProduct(
    val itemId: String,
    categoryId: String,
    name: String,
    imageUrl: String,
    salePrice: String
) : Product(categoryId, name, imageUrl, salePrice)