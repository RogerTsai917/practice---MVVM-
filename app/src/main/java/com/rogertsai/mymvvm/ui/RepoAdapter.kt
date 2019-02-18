package com.rogertsai.mymvvm.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rogertsai.mymvvm.data.model.Repo
import com.rogertsai.mymvvm.databinding.RepoItemBinding
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.support.v7.util.DiffUtil
import java.util.*

class RepoAdapter(private var items: MutableList<Repo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RepoViewHolder(internal val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
            //需特別注意的是bind(repo)當中的executePendingBindings()，這會讓binding立即更新畫面，
            //所以每次onBindViewHolder後都會立刻更新，以免快速滑動等情況導致UI顯示錯誤。
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) {
            bindRepoViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun swapItems(newItems: List<Repo>?) {
        if (newItems == null) {
            val oldSize = items.size
            items.clear()
            notifyItemRangeChanged(0, oldSize)
        } else {
            val result = DiffUtil.calculateDiff(RepoDiffCallback(this.items, newItems))
            this.items.clear()
            this.items.addAll(newItems)
            result.dispatchUpdatesTo(this)
        }
    }

    private fun bindRepoViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = items[position]
        holder.bind(repo)
        Glide.with(holder.binding.root.context)
                .load(repo.owner.avatarUrl)
                .into(holder.binding.ownerAvatar)
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