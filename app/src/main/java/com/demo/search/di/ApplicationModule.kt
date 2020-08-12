package com.demo.search.di

import android.app.Application
import android.content.Context
import com.base.domain.UseCaseComposer
import com.base.networking.LiveNetworkMonitor
import com.base.networking.NetworkClient
import com.base.usecases.AndroidUseCaseComposer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideNetworkClient(app: Application, liveNetworkMonitor: LiveNetworkMonitor): NetworkClient {
        return NetworkClient.getInstance(app, liveNetworkMonitor)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(context: Context): LiveNetworkMonitor {
        return LiveNetworkMonitor(context)
    }

    @Provides
    fun provideUseCaseComposer(): UseCaseComposer {
        return AndroidUseCaseComposer()
    }

}