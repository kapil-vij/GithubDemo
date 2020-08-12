package com.demo.search.di

import androidx.lifecycle.ViewModelProvider
import com.base.views.AppViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by kapil.vij on 12.08.2020..
 */
@Suppress("unused")
@Module
abstract class ViewModelFatoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}