package com.mobiquity.testapp.presentation.model.factory

import android.content.Context
import com.mobiquity.testapp.R
import com.mobiquity.testapp.domain.model.DrinkProduct
import com.mobiquity.testapp.domain.model.FoodProduct
import com.mobiquity.testapp.domain.model.Product
import com.mobiquity.testapp.presentation.model.HeaderProductViewData
import com.mobiquity.testapp.presentation.model.ProductViewData
import com.mobiquity.testapp.presentation.model.converter.ProductToItemProductViewDataConverter
import javax.inject.Inject

class ProductViewDataFactory @Inject constructor(
    private val context: Context,
    private val converter: ProductToItemProductViewDataConverter
) {

    fun create(products: List<Product>): List<ProductViewData> {
        val productViewDataList = mutableListOf<ProductViewData>()

        val foodProducts = products.filterIsInstance(FoodProduct::class.java)
        productViewDataList.add(HeaderProductViewData(context.getString(R.string.food_header)))
        productViewDataList.addAll(foodProducts.map { converter(it) })

        val drinkProducts = products.filterIsInstance(DrinkProduct::class.java)
        productViewDataList.add(HeaderProductViewData(context.getString(R.string.drink_header)))
        productViewDataList.addAll(drinkProducts.map { converter(it) })

        return productViewDataList
    }
}