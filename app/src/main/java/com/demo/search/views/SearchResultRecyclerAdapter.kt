package com.demo.search.views

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demo.search.R
import com.demo.search.data.model.SearchResultData
import com.demo.search.databinding.RecyclerItemSearchBinding


class SearchResultRecyclerAdapter(private var activity: Activity?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userList: List<SearchResultData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            (parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.recycler_item_search,
                parent,
                false
            )
        return UsersViewHolder(view)

    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: RecyclerItemSearchBinding = DataBindingUtil.bind(itemView)!!

        fun bind(position: Int) {
            binding.dataModel = userList?.get(position)
            binding.executePendingBindings()

            userList?.get(position)?.avatar_url?.let {
                Glide.with(activity!!)
                    .load(it)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.imgUserProfile);
            }

            binding.txvViewRepos.setOnClickListener {
                if(activity is ViewRepoListener){
                    (activity as ViewRepoListener).onViewRepoSelected(userList?.get(position)?.login)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as UsersViewHolder).bind(position)
    }


    fun setData(userList: List<SearchResultData>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}