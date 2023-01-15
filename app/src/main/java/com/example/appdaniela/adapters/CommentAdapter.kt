package com.example.appdaniela.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaniela.databinding.CommentItemLayoutBinding
import com.example.appdaniela.models.CommentDto

class CommentAdapter: PagingDataAdapter<CommentDto, CommentAdapter.ItemLayoutViewHolder>(ItemComp) {

    inner class ItemLayoutViewHolder(private val binding: CommentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommentDto) = with(binding){
            commentItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  =
        ItemLayoutViewHolder(
            CommentItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ItemLayoutViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    object ItemComp: DiffUtil.ItemCallback<CommentDto>() {
        override fun areItemsTheSame(oldItem: CommentDto, newItem: CommentDto) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CommentDto, newItem: CommentDto) =
            oldItem == newItem
    }


}