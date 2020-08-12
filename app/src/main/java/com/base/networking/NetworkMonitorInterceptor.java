/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking;

import androidx.annotation.NonNull;


import com.base.exception.NoNetworkException;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by kapilvij on 25/05/20.
 */

public class NetworkMonitorInterceptor implements Interceptor {

    private LiveNetworkMonitor mLiveNetworkMonitor;


    @Inject
    public NetworkMonitorInterceptor(LiveNetworkMonitor liveNetworkMonitor) {
        this.mLiveNetworkMonitor = liveNetworkMonitor;
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        if (mLiveNetworkMonitor.isConnected()) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException();
        }
    }
}