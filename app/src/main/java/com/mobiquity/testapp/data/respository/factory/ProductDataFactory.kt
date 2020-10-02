package com.mobiquity.testapp.data.respository.factory

import com.mobiquity.testapp.data.converter.RemoteProductToProductConverter
import com.mobiquity.testapp.data.service.response.RemoteProduct
import com.mobiquity.testapp.data.service.response.RemoteProductData
import com.mobiquity.testapp.domain.model.Product
import com.mobiquity.testapp.domain.model.ProductData
import javax.inject.Inject

class ProductDataFactory @Inject constructor() {

    fun create(remoteProducts: List<RemoteProductData>): ProductData {
        return ProductData(getProducts(remoteProducts))
    }

    private fun getProducts(remoteProducts: List<RemoteProductData>): List<Product> {
        return remoteProducts.flatMap { mapProductsForCategory(it.categoryId, it.categoryName, it.products) }
    }

    private fun mapProductsForCategory(
        categoryId: String,
        categoryName: String,
        products: List<RemoteProduct>
    ): List<Product> {
        return products.map { RemoteProductToProductConverter(categoryId, categoryName).invoke(it) }
    }
}