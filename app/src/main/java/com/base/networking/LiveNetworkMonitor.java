/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by kapilvij on 25/05/20.
 */

public class LiveNetworkMonitor {

    private final Context context;

    @Inject
    public LiveNetworkMonitor(final Context context) {
        this.context = context;
    }

    protected boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}