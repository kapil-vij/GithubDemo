package com.resonance.main.di

import com.demo.search.views.RepoListActivity
import com.demo.search.views.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
interface ActivityBuilder {

    @ContributesAndroidInjector
    fun contributeNewsFeedActivity(): SearchActivity

    @ContributesAndroidInjector
    fun contributeRepoListActivity(): RepoListActivity

}