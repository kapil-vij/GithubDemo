package com.demo.search

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.demo.search.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SearchApplication: MultiDexApplication(), HasAndroidInjector {

    @Inject lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().applicationBind(this).build().inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}