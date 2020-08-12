package com.demo.search.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.views.BaseActivity
import com.base.widget.VerticalItemDecoration
import com.demo.search.R
import com.demo.search.constants.AppConstants
import com.demo.search.databinding.ActivityRepoListBinding


class RepoListActivity : BaseActivity() {


    private lateinit var binding: ActivityRepoListBinding
    private lateinit var viewModel: RepoListViewModel
    private val adapter = RepoListRecyclerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoListViewModel::class.java)

        binding.viewModel = viewModel

        intent?.getStringExtra(AppConstants.BundleKeys.USERNAME)?.let {
            viewModel.searchRepos(it)
        }

        setUpAdapter()
        subscribeObservers()
    }

    private fun setUpAdapter() {
        binding.rvRepos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRepos.addItemDecoration(
            VerticalItemDecoration(
                resources?.getDimension(R.dimen.dimen_10dp)?.toInt() ?: 0
            )
        )

        binding.rvRepos.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.searchResultsLiveData.observe(this, Observer {
            adapter.setData(it)
        })

    }

}