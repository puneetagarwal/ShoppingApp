package com.mobiquity.testapp.core.presentation

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import java.io.File

interface ImageLoader {

    fun load(imageView: ImageView, imageUri: Uri)
}
