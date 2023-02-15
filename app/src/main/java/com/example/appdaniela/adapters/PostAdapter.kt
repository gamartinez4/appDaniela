package com.example.appdaniela.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaniela.databinding.PostItemLayoutBinding
import com.example.appdaniela.models.FoodModDto

class PostAdapter: PagingDataAdapter<FoodModDto,  PostAdapter.ItemLayoutViewHolder>(ItemComp) {

    var onClickFunction:((FoodModDto)->Unit)? = null

    inner class ItemLayoutViewHolder(private val binding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickFunction!!(binding.gitItem!!)
            }
        }

        fun bind(item: FoodModDto) = with(binding){
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


    object ItemComp: DiffUtil.ItemCallback<FoodModDto>() {
        override fun areItemsTheSame(oldItem:  FoodModDto, newItem:  FoodModDto) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem:  FoodModDto, newItem:  FoodModDto) =
            oldItem == newItem
    }


}