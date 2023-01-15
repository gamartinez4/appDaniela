package com.example.appdaniela.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaniela.databinding.PostItemLayoutBinding
import com.example.appdaniela.models.PostDto

class PostAdapter: PagingDataAdapter<PostDto,  PostAdapter.ItemLayoutViewHolder>(ItemComp) {

    var onClickFunction:((PostDto)->Unit)? = null

    inner class ItemLayoutViewHolder(private val binding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickFunction!!(binding.gitItem!!)
            }
        }

        fun bind(item: PostDto) = with(binding){
            gitItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemLayoutViewHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ItemLayoutViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    object ItemComp: DiffUtil.ItemCallback<PostDto>() {
        override fun areItemsTheSame(oldItem:  PostDto, newItem:  PostDto) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem:  PostDto, newItem:  PostDto) =
            oldItem == newItem
    }


}