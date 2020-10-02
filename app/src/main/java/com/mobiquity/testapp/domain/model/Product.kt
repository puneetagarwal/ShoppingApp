package com.mobiquity.testapp.domain.model

abstract class Product(
    val categoryId: String,
    val name: String,
    val imageUrl: String,
    val salePrice: String
)