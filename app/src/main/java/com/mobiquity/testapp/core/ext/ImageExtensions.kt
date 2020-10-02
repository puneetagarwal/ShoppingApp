package com.mobiquity.testapp.core.ext

import android.net.Uri
import android.widget.ImageView
import com.mobiquity.testapp.core.di.CoreInjector

fun ImageView.loadImage(imageUri: Uri) {
    CoreInjector.component.getImageLoader().load(this, imageUri)
}
