package com.demo.search.di

import android.app.Application
import com.demo.search.SearchApplication
import com.resonance.main.di.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityBuilder::class, ViewModelFatoryModule::class])
interface ApplicationComponent {

    fun inject(application: SearchApplication)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder

    }
}