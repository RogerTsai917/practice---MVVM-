package com.rogertsai.mymvvm.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rogertsai.mymvvm.data.model.Repo
import com.rogertsai.mymvvm.databinding.RepoItemBinding
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.support.v7.util.DiffUtil
import java.util.*


class RepoAdapter(private var items: MutableList<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(internal val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = items[position]

        Glide.with(holder.itemView.context)
                .load(repo.owner.avatar_url)
                .into(holder.binding.ownerAvatar)

        holder.binding.name.text = repo.name
        holder.binding.desc.text = repo.description
        holder.binding.stars.text = repo.stars.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun swapItems(newItems: List<Repo>) {
        val result = DiffUtil.calculateDiff(RepoDiffCallback(this.items, newItems))
        this.items.clear()
        this.items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }


    private inner class RepoDiffCallback internal constructor(private val mOldList: List<Repo>?, private val mNewList: List<Repo>?) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return mOldList?.size ?: 0
        }

        override fun getNewListSize(): Int {
            return mNewList?.size ?: 0
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldId = mOldList!![oldItemPosition].id
            val newId = mNewList!![newItemPosition].id
            return Objects.equals(oldId, newId)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRepo = mOldList!![oldItemPosition]
            val newRepo = mNewList!![newItemPosition]
            return Objects.equals(oldRepo, newRepo)
        }
    }

}