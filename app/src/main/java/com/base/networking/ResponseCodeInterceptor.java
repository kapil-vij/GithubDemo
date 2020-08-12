/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking;

import android.content.Context;

import androidx.annotation.NonNull;


import com.base.errors.HttpStatus;
import com.base.exception.NoContentException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kapilvij on 25/05/20.
 */

public class ResponseCodeInterceptor implements Interceptor {


    private final Context context;

    public ResponseCodeInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final int responseCode = response.code();
        if (responseCode == HttpStatus.NO_CONTENT) {
            throw new NoContentException();
        } else if (responseCode == HttpStatus.FORBIDDEN) {
            handleTokenExpired(context);
        }
        return response;

    }

    private void handleTokenExpired(Context context) {

    }
}