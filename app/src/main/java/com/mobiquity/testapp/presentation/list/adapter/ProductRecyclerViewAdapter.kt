package com.mobiquity.testapp.presentation.list.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.testapp.BuildConfig
import com.mobiquity.testapp.R
import com.mobiquity.testapp.core.ext.loadImage
import com.mobiquity.testapp.presentation.model.HeaderProductViewData
import com.mobiquity.testapp.presentation.model.ItemProductViewData
import com.mobiquity.testapp.presentation.model.ProductViewData
import kotlinx.android.synthetic.main.layout_item_header.view.*
import kotlinx.android.synthetic.main.layout_item_product_list.view.*

class ProductRecyclerViewAdapter(
    private val listener: OnItemClickListener,
    private val products: List<ProductViewData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_HEADER -> HeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_header, parent, false)
        )
        TYPE_ITEM -> ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_product_list, parent, false)
        )
        else -> throw IllegalArgumentException("Unknown view type")
    }

    override fun getItemViewType(position: Int): Int = when (products[position]) {
        is HeaderProductViewData -> TYPE_HEADER
        is ItemProductViewData -> TYPE_ITEM
        else -> throw IllegalArgumentException("Unknown view type")
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_HEADER -> onBindHeader(holder, products[position] as HeaderProductViewData)
            TYPE_ITEM -> onBindItem(holder, products[position] as ItemProductViewData)
        }
    }

    private fun onBindHeader(holder: RecyclerView.ViewHolder, data: HeaderProductViewData) {
        val header = holder as HeaderViewHolder
        header.nameView.text = data.name
    }

    private fun onBindItem(holder: RecyclerView.ViewHolder, data: ItemProductViewData) {
        val item = holder as ItemViewHolder
        item.itemImageView.loadImage(Uri.parse("${BuildConfig.BASE_URL}${data.imageUrl}"))
        item.nameView.text = data.name
        item.priceView.text = data.salePrice
        holder.itemView.setOnClickListener { listener.onItemClicked(data) }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.textHeaderName
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageView: ImageView = itemView.imageProduct
        val nameView: TextView = itemView.textProductName
        val priceView: TextView = itemView.textProductPrice
    }

    interface OnItemClickListener {

        fun onItemClicked(item: ItemProductViewData)
    }
}