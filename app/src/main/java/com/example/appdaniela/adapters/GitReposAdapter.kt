package com.example.appdaniela.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaniela.databinding.ItemLayoutBinding
import com.example.appdaniela.models.GitRepListInfo

class GitReposAdapter: PagingDataAdapter<GitRepListInfo,  GitReposAdapter.ItemLayoutViewHolder>(ItemComp) {

    var onClickFunction:((GitRepListInfo)->Unit)? = null

    inner class ItemLayoutViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickFunction!!(binding.gitItem!!)
            }
        }

        fun bind(item:  GitRepListInfo) = with(binding){
            gitItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemLayoutViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ItemLayoutViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    object ItemComp: DiffUtil.ItemCallback< GitRepListInfo>() {
        override fun areItemsTheSame(oldItem:  GitRepListInfo, newItem:  GitRepListInfo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem:  GitRepListInfo, newItem:  GitRepListInfo) =
            oldItem == newItem
    }


}