package com.demo.search.views

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.search.R
import com.demo.search.data.model.ReposResponseEntity
import com.demo.search.databinding.RecyclerItemRepoBinding


class RepoListRecyclerAdapter(private var activity: Activity?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repoList: List<ReposResponseEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            (parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.recycler_item_repo,
                parent,
                false
            )
        return RepoViewHolder(view)

    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: RecyclerItemRepoBinding = DataBindingUtil.bind(itemView)!!

        fun bind(position: Int) {
            binding.dataModel = repoList?.get(position)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return repoList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as RepoViewHolder).bind(position)
    }


    fun setData(repoList: List<ReposResponseEntity>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}