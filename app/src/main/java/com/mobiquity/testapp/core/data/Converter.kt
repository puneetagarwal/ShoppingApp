package com.mobiquity.testapp.core.data

interface Converter<S, T> {

    operator fun invoke(source: S): T
}