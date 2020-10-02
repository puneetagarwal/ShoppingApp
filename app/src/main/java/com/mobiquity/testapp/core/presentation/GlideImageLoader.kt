package com.mobiquity.testapp.core.presentation

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mobiquity.testapp.R

class GlideImageLoader : ImageLoader {

    override fun load(imageView: ImageView, imageUri: Uri) {
        Glide.with(imageView.context)
            .load(imageUri)
            .error(R.drawable.ic_error_image)
            .into(imageView)
    }
}
