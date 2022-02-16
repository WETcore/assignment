package com.aqua.snapask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aqua.snapask.data.GithubUser
import com.aqua.snapask.databinding.ItemBinding
import com.bumptech.glide.Glide

class UserAdapter() : ListAdapter<GithubUser, UserAdapter.UserViewHolder>(DiffCallback) {

    class UserViewHolder(var binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)

        holder.binding.itemText.text = user.login
        Glide
            .with(holder.binding.root)
            .load(user.avatar_url.toUri())
            .into(holder.binding.image)

        holder.bind()
    }
}