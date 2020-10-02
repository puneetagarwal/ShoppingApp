package com.mobiquity.testapp.data.converter

import com.mobiquity.testapp.core.data.Converter
import com.mobiquity.testapp.data.service.response.RemoteProduct
import com.mobiquity.testapp.data.service.response.RemoteProductPrice
import com.mobiquity.testapp.domain.model.DrinkProduct
import com.mobiquity.testapp.domain.model.FoodProduct
import com.mobiquity.testapp.domain.model.Product
import java.lang.IllegalArgumentException

class RemoteProductToProductConverter(
    private val categoryId: String,
    private val categoryName: String
) : Converter<RemoteProduct, Product> {

    override fun invoke(source: RemoteProduct): Product {
        return when (categoryName) {
            "Food" -> createFoodProduct(source)
            "Drink" -> createDrinkProduct(source)
            else -> throw IllegalArgumentException("Unknown product type")
        }
    }

    private fun createFoodProduct(remoteProduct: RemoteProduct): FoodProduct {
        return with(remoteProduct) {
            FoodProduct(
                itemId = id,
                categoryId = categoryId,
                name = name,
                imageUrl = imageUrl ?: "",
                salePrice = getSalePrice(price)
            )
        }
    }

    private fun createDrinkProduct(remoteProduct: RemoteProduct): DrinkProduct {
        return with(remoteProduct) {
            DrinkProduct(
                itemId = id,
                categoryId = categoryId,
                name = name,
                imageUrl = imageUrl ?: "",
                salePrice = getSalePrice(price)
            )
        }
    }

    private fun getSalePrice(remoteProductPrice: RemoteProductPrice): String {
        return "${remoteProductPrice.currency} ${remoteProductPrice.amount}"
    }
}