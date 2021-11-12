package com.example.newsapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapi.R
import com.example.newsapi.data.ArticlesItem
import com.example.newsapi.databinding.ItemThumbnailBinding

class ThumbnailAdapter(
    private var listData : MutableList<ArticlesItem?>,
    private var onItemClickCallback: OnItemClickCallback
): RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<ArticlesItem?>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemThumbnailBinding.bind(itemView)
        fun bind(data: ArticlesItem) {
            with(binding) {
                txtName.text = data.source?.name
                txtPublished.text = data.publishedAt
                txtDesc.text = data.description
                imgThumbnail.load(data.urlToImage) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(android.R.color.darker_gray)
                    error(R.drawable.ic_launcher_background)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_thumbnail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position]!!)
    }

    override fun getItemCount(): Int = listData.size

    interface OnItemClickCallback {
        fun onItemClick(data: ArticlesItem)
    }

}