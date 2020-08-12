package com.demo.search.views

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.views.BaseActivity
import com.base.widget.VerticalItemDecoration
import com.demo.search.R
import com.demo.search.constants.AppConstants
import com.demo.search.databinding.ActivitySearchBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class SearchActivity :BaseActivity(), ViewRepoListener{


    private val THRESHOLD: Int = 2
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private val adapter = SearchResultRecyclerAdapter(this)

    private val TEXT_CHANGE_TIME_IN_MILLIS: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        binding.viewModel = viewModel

        RxTextView.textChanges(binding.edtSearch)
            .skip(3)
            .debounce(
                TEXT_CHANGE_TIME_IN_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ chars ->
                if(chars?.length?:0 > THRESHOLD) {
                    viewModel.searchUser(chars?.toString())
                } else {
                    viewModel.clearSearch()
                }
            }, { throwable -> })

        setUpAdapter()
        subscribeObservers()
    }

    private fun setUpAdapter() {
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.addItemDecoration(
            VerticalItemDecoration(
                resources?.getDimension(R.dimen.dimen_10dp)?.toInt() ?: 0
            )
        )

        binding.rvUsers.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.searchResultsLiveData.observe(this, Observer {
            adapter.setData(it)
        })

    }

    override fun onViewRepoSelected(userName: String?) {
        val repoIntent = Intent(this, RepoListActivity::class.java)
        repoIntent.putExtra(AppConstants.BundleKeys.USERNAME, userName)
        startActivity(repoIntent)
    }
}