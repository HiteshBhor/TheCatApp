package com.hpb.thecatapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpb.thecatapp.R
import com.hpb.thecatapp.models.CatModelItem

class CatPagingAdapter :
    PagingDataAdapter<CatModelItem, CatPagingAdapter.CatViewHolder>(COMPARATOR) {

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        val item = getItem(position)
        if (item != null) {

            Glide.with(holder.imageView.context)
                .load(item.url)
                .apply(
                    RequestOptions.centerCropTransform().override(
                        item.width,
                        item.height
                    )
                )
                .into(holder.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CatModelItem>() {
            override fun areItemsTheSame(oldItem: CatModelItem, newItem: CatModelItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatModelItem, newItem: CatModelItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}