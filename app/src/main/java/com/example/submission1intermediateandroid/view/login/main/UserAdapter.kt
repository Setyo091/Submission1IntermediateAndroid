package com.example.submission1intermediateandroid.view.login.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1intermediateandroid.databinding.ItemStoryBinding
import com.example.submission1intermediateandroid.view.login.detail.DetailActivity
import com.example.submission1intermediateandroid.response.ListStoryItem

class UserAdapter : PagingDataAdapter<ListStoryItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    inner class MyViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemName: ListStoryItem) {
            binding.isName.text = itemName.name
            Glide.with(binding.root)
                .load(itemName.photoUrl)
                .into(binding.isPhoto)
            binding.root.setOnClickListener {
                val intentDetail = Intent(binding.root.context, DetailActivity::class.java)
                intentDetail.putExtra(DetailActivity.ID, itemName.id)
                intentDetail.putExtra(DetailActivity.NAME, itemName.name)
                intentDetail.putExtra(DetailActivity.DESCRIPTION, itemName.description)
                intentDetail.putExtra(DetailActivity.PICTURE, itemName.photoUrl)
                binding.root.context.startActivity(intentDetail)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}