package com.aqua.snapask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aqua.snapask.data.GithubUser
import com.aqua.snapask.databinding.Item2Binding
import com.aqua.snapask.databinding.ItemBinding
import com.bumptech.glide.Glide

const val TYPE_ONE = 0
const val TYPE_TWO = 1

class UserAdapter() : ListAdapter<GithubUser, RecyclerView.ViewHolder>(DiffCallback) {

    class UserViewHolder(var binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    class UserViewHolder2(var binding: Item2Binding): RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ONE -> UserViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> UserViewHolder2(Item2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)

        when(holder) {
            is UserViewHolder -> {
                holder.binding.itemText.text = user.login
                Glide
                    .with(holder.binding.root)
                    .load(user.avatar_url.toUri())
                    .into(holder.binding.image)
            }
            is UserViewHolder2 -> {
                holder.binding.itemText.text = user.login
                Glide
                    .with(holder.binding.root)
                    .load(user.avatar_url.toUri())
                    .into(holder.binding.image)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % 2) {
            0 -> TYPE_ONE
            else -> TYPE_TWO
        }
    }
}