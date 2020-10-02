package com.mobiquity.testapp.presentation.model.converter

import com.mobiquity.testapp.core.data.Converter
import com.mobiquity.testapp.domain.model.Product
import com.mobiquity.testapp.presentation.model.ItemProductViewData
import javax.inject.Inject

class ProductToItemProductViewDataConverter @Inject constructor() : Converter<Product, ItemProductViewData> {

    override fun invoke(source: Product): ItemProductViewData {
        return with(source) {
            ItemProductViewData(name, imageUrl, salePrice)
        }
    }
}