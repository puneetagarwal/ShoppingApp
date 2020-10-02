package com.mobiquity.testapp.domain.repository

import com.mobiquity.testapp.domain.model.ProductData
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(): Single<ProductData>
}